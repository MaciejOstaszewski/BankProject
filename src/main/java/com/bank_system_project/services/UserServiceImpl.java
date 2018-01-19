/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;


import com.bank_system_project.configuration.ProfileNames;
import com.bank_system_project.exceptions.TemplateNotFoundException;
import com.bank_system_project.exceptions.UserNotFoundException;
import com.bank_system_project.models.Role;
import com.bank_system_project.repositories.RoleRepository;
import com.bank_system_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Profile(ProfileNames.DATABASE)
public class UserServiceImpl implements UserService {

    Authentication auth;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Sprawdza autentykacje użytkownika poodczas logowania
     * @param username nazwa logującego się użytkownika
     * @throws UsernameNotFoundException nie znaleziono uzytkownika o danej nazwie
     * @return dane uwierzytelniające użytkownika
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.bank_system_project.models.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (!user.isEnabled()){
            throw new UsernameNotFoundException(username);
        }
        return convertToUserDetails(user);
    }

    private UserDetails convertToUserDetails(com.bank_system_project.models.User user) {
        Set<GrantedAuthority> grantedAuthorities =
                user.getRoles().stream().map(//mapowanie Role na GrantedAuthority
                        r -> new SimpleGrantedAuthority(r.getType().toString())
                ).collect(Collectors.toSet());

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);//klasa ma też drugi konstruktor – więcej parametrów
    }


    /**
     * ustawia role użytkownikowi, tworzy hasz hasła i zapisuje użytkownika do bazy
     * @param user użytkownik do zapisania
     */
    @Override
    public void save(com.bank_system_project.models.User user) {

        Role userRole = roleRepository.findRoleByType(Role.Types.ROLE_USER);
        List roles = Arrays.asList(userRole);
        user.setRoles(new HashSet<>(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    /**
     * Aktualizuje użytkownika
     * @param user użtykownik do zaktualizowania
     */
    @Override
    public void update(com.bank_system_project.models.User user) {
        userRepository.save(user);
    }

    /**
     * Sprawdza czy nazwa użtkownika jest unikalna
     * @param username nazwa użytkonika do sprawdzenia
     * @return informacja o tym czy nazwa jest unikalna
     */
    @Override
    public boolean isUniqueLogin(String username) {
        return userRepository.findByUsername(username) == null;
    }


    /**
     * Pobiera liste zablokowanych użtykowników
     * @return lista zablokowanych użtykowników
     */
    @Override
    public List<com.bank_system_project.models.User> findDisabled() {
        return userRepository.findAllByEnabledIsFalse();
    }


    /**
     * Usuwa użytkownika
     * @param id id użytkonika do usunięcia
     */
    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    /**
     * Pobiera użtkownika
     * @param id id uzytkownika
     * @throws UserNotFoundException nie znaleziono uzytkownika o danym id
     * @return użytkownik o danym id
     */
    @Override
    public com.bank_system_project.models.User getUserById(long id) {
        Optional<com.bank_system_project.models.User> optionalUser = userRepository.findById(id);
        com.bank_system_project.models.User user = optionalUser.orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    /**
     * Pobiera aktualnie zalogowaniego użtkownika
     * @return aktualnie zalogowany użtkownik
     */
    @Override
    public com.bank_system_project.models.User getCurrentLoggedUser() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

    /**
     * Pobiera użtkownika
     * @param username nazwa uzytkownika
     * @return użytkownik o danej nazwie
     */
    @Override
    public com.bank_system_project.models.User getUserByUsername(String username) {
        return userRepository.findByUsername(username);

    }
    /**
     * Pobiera nazwe zagogowanego użtkownika
     * @return nazwa użytkownika
     */
    @Override
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }



}

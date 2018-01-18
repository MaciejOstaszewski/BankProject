package com.bank_system_project.services;


import com.bank_system_project.configuration.ProfileNames;
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

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.bank_system_project.models.User user = userRepository.findByUsername(username);
        if (user == null) {
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

    @Override
    public void save(com.bank_system_project.models.User user) {

        Role userRole = roleRepository.findRoleByType(Role.Types.ROLE_USER);
        List roles = Arrays.asList(userRole);
        user.setRoles(new HashSet<>(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    @Override
    public void update(com.bank_system_project.models.User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isUniqueLogin(String username) {
        return userRepository.findByUsername(username) == null;
    }

    @Override
    public List<com.bank_system_project.models.User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<com.bank_system_project.models.User> findDisabled() {
        return userRepository.findAllByEnabledIsFalse();
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public com.bank_system_project.models.User getUserById(long id) {
        Optional<com.bank_system_project.models.User> optionalUser = userRepository.findById(id);
        com.bank_system_project.models.User user = optionalUser.orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }



    @Override
    public com.bank_system_project.models.User fillNewUser(com.bank_system_project.models.User user) {
//        user.setAccountNumber(generateAccountNumber());
//        user.setMeans(BigDecimal.valueOf(0));
//        user.setUsername(user.getEmial());
        return user;
    }

    @Override
    public com.bank_system_project.models.User getCurrentLoggedUser() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

    @Override
    public BigDecimal getUserMeans() {
        return null;// getCurrentLoggedUser().getMeans();
    }

    @Override
    public com.bank_system_project.models.User getUserByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public boolean isExist(long id) {
        return userRepository.existsById(id);
    }

}

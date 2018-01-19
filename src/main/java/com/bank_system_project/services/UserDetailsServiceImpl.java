/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;


import com.bank_system_project.exceptions.UserNotFoundException;
import com.bank_system_project.models.User;
import com.bank_system_project.models.UserDetails;
import com.bank_system_project.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    /**
     * Zapisuje dane użytkowika do bazy
     * @param userDetails dane użytkowika do zapisania
     */
    @Override
    public void save(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }


    /**
     * Uzupełnia i zapisuje dane użytkowika do bazy
     * @param userDetails dane użytkowika do zapisania
     */
    @Override
    public void fillAndSave(UserDetails userDetails) {
        userDetails.setAccountNumber(generateAccountNumber());
        userDetails.setMeans(BigDecimal.valueOf(0));
        userDetailsRepository.save(userDetails);

    }

    /**
     * Pobiera dane użtkownika
     * @param id id danych uzytkownika
     * @throws UserNotFoundException nie znaleziono uzytkownika o danym id
     * @return dane użytkownika o danym id
     */
    @Override
    public UserDetails getOne(long id) {
        Optional<UserDetails> optionalUserDetails = userDetailsRepository.findById(id);
        UserDetails userDetails = optionalUserDetails.orElseThrow(() -> new UserNotFoundException(id));
        return userDetails;
    }


    /**
     * Generuje numer konta nowego użytkownika
     * @return numer konta
     */
    @Override
    public String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int character;
        for(int i = 0 ; i < 26 ; i++) {
            character = random.nextInt(10);
            sb.append(character);
        }

        return sb.toString();
    }

}

/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;

import com.bank_system_project.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    void save(User user);

    void update(User user);

    boolean isUniqueLogin(String login);



    List<User> findDisabled();

    void deleteUserById(long id);

    User getUserById(long id);


    User getCurrentLoggedUser();


    User getUserByUsername(String username);

    String getUsername();


}

/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;

import com.bank_system_project.models.UserDetails;

public interface UserDetailsService {

    void save(UserDetails userDetails);

    void fillAndSave(UserDetails userDetails);

    UserDetails getOne(long id);

    String generateAccountNumber();
}

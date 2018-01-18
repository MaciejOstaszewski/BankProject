package com.bank_system_project.services;

import com.bank_system_project.models.UserDetails;

public interface UserDetailsService {

    void save(UserDetails userDetails);

    void fillAndSave(UserDetails userDetails);

    UserDetails getOne(long id);

    String generateAccountNumber();
}

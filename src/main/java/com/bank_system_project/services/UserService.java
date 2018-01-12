package com.bank_system_project.services;

import com.bank_system_project.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);

    boolean isUniqueLogin(String login);
}

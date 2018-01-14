package com.bank_system_project.repositories;


import com.bank_system_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String s);


    List<User> findAllByEnabledIsFalse();
}

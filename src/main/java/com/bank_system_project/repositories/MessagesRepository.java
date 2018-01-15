package com.bank_system_project.repositories;

import com.bank_system_project.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long>{

    List<Messages> findAllByUserUsername(String username);

}

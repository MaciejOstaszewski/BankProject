package com.bank_system_project.repositories;

import com.bank_system_project.models.Transfer;
import com.bank_system_project.models.TransferTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferTemplateRepository extends JpaRepository<TransferTemplate, Long> {
    List<TransferTemplate> findAllByUserUsername(String username);



}

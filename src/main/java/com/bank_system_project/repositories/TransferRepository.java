package com.bank_system_project.repositories;

import com.bank_system_project.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {


    @Query("SELECT t FROM Transfer t WHERE (t.status NOT LIKE 'Wysłany')")
    List<Transfer> findAllByUserUsername(String username);
}

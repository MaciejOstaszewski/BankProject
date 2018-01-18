package com.bank_system_project.repositories;

import com.bank_system_project.models.TransactionsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransactionsHistoryRepository extends JpaRepository<TransactionsHistory, Long> {
    List<TransactionsHistory> findAllByUserUsername(String username);


    @Query("SELECT t from TransactionsHistory t WHERE t.realizationDate between :start AND :fin")
    List<TransactionsHistory> findAllByUserUsername(String username, Date start, Date fin);
}

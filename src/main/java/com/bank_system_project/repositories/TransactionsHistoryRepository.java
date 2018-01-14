package com.bank_system_project.repositories;

import com.bank_system_project.models.TransactionsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsHistoryRepository extends JpaRepository<TransactionsHistory, Long> {

}

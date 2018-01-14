package com.bank_system_project.services;

import com.bank_system_project.models.TransactionsHistory;
import com.bank_system_project.models.Transfer;

public interface TransactionsHistoryService {
    void Save(Transfer transfer);
}

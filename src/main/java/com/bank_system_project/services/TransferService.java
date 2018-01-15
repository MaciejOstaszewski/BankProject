package com.bank_system_project.services;

import com.bank_system_project.models.Transfer;

import java.util.List;

public interface TransferService {

    void save(Transfer transfer);

    void saveTopUp(Transfer transfer);

    List<Transfer> getCurrentLoggedUserTransfers(String username);
}

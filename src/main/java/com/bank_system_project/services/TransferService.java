package com.bank_system_project.services;

import com.bank_system_project.models.Transfer;

import java.util.List;

public interface TransferService {

    void save(Transfer transfer);

    void delete(long id);

    List<Transfer> getCurrentLoggedUserTransfers(String username);

    Transfer getOne(long id);
}

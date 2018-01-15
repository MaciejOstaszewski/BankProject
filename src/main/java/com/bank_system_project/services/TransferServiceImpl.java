package com.bank_system_project.services;


import com.bank_system_project.models.Transfer;
import com.bank_system_project.repositories.TransferRepository;
import com.bank_system_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferRepository transferRepository;


    @Autowired
    UserRepository userRepository;

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public void saveTopUp(Transfer transfer) {


        transferRepository.save(transfer);
    }

    @Override
    public List<Transfer> getCurrentLoggedUserTransfers(String username) {
        return transferRepository.findAllByUserUsername(username);
    }
}

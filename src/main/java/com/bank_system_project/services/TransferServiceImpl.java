package com.bank_system_project.services;


import com.bank_system_project.models.Transfer;
import com.bank_system_project.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public void saveTopUp(Transfer transfer) {


        transferRepository.save(transfer);
    }
}

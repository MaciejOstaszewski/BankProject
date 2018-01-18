package com.bank_system_project.services;


import com.bank_system_project.exceptions.MessageNotFoundException;
import com.bank_system_project.exceptions.TransferNotFoundException;
import com.bank_system_project.models.Transfer;
import com.bank_system_project.repositories.TransferRepository;
import com.bank_system_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void delete(long id) {
        transferRepository.deleteById(id);
    }


    @Override
    public List<Transfer> getCurrentLoggedUserTransfers(String username) {
        return transferRepository.findAllByUserUsername(username);
    }

    @Override
    public Transfer getOne(long id) {
        Optional<Transfer> optionalTransfer = transferRepository.findById(id);
        Transfer transfer = optionalTransfer.orElseThrow(() -> new TransferNotFoundException(id));
        return transfer;
    }
}

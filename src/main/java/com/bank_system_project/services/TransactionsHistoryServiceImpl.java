/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;


import com.bank_system_project.models.*;
import com.bank_system_project.repositories.MobileNetworkRepository;
import com.bank_system_project.repositories.TransactionsHistoryRepository;
import com.bank_system_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsHistoryServiceImpl implements TransactionsHistoryService {


    @Autowired
    TopUpService topUpService;

    @Autowired
    TransactionsHistoryRepository transactionsHistoryRepository;

    @Autowired
    UserService userService;


    @Override
    public void save(Transfer transfer) {
        TransactionsHistory transactionsHistory = new TransactionsHistory();
        transactionsHistory.setRecipient(transfer.getRecipientsNameAndAdress());
        transactionsHistory.setAmount(transfer.getAmount());
        transactionsHistory.setUser(transfer.getUser());
        transactionsHistory.setRealizationDate(transfer.getExecutionDate());
        transactionsHistory.setRecipientsAccount(transfer.getRecipientsAccount());
        transactionsHistory.setTransactionType("Przelew");
        transactionsHistoryRepository.save(transactionsHistory);

    }

    @Override
    public void save(TopUp topUp) {
        TransactionsHistory transactionsHistory = new TransactionsHistory();

        transactionsHistory.setRecipient(topUpService.getMobileNetworkName(topUp.getMobileNetwork().getId()));
        transactionsHistory.setUser(topUp.getUser());
        transactionsHistory.setAmount(topUp.getAmount());
        transactionsHistory.setRealizationDate(topUp.getExecutionDate());
        transactionsHistory.setRecipientsAccount(topUp.getPhoneNumber());
        transactionsHistory.setTransactionType("Doładowanie Telefonu");
        transactionsHistoryRepository.save(transactionsHistory);
    }

    @Override
    public List<TransactionsHistory> getAll(String name) {
        return transactionsHistoryRepository.findAllByUserUsername(name);
    }

    @Override
    public List<TransactionsHistory> getAllByTimeInterval(String name, Date date1, Date date2) {
        return transactionsHistoryRepository.findAllTransactionsHistory(name, date1, date2);
    }


}

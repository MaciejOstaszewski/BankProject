package com.bank_system_project.services;


import com.bank_system_project.models.TransactionsHistory;
import com.bank_system_project.models.Transfer;
import com.bank_system_project.models.User;
import com.bank_system_project.repositories.TransactionsHistoryRepository;
import com.bank_system_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TransactionsHistoryServiceImpl implements TransactionsHistoryService {



    @Autowired
    TransactionsHistoryRepository transactionsHistoryRepository;

    @Autowired
    UserService userService;

    @Override
    public void Save(Transfer transfer) {
//        TransactionsHistory transactionsHistory = new TransactionsHistory();
//        User user = userService.getCurrentLoggedUser();
//        transactionsHistory.setUser(user);
//        transactionsHistory.getTransfer().add(transfer);
//        transactionsHistoryRepository.save(transactionsHistory);
    }




}

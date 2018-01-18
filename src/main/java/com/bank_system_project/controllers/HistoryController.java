package com.bank_system_project.controllers;


import com.bank_system_project.services.TransactionsHistoryService;
import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HistoryController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionsHistoryService transactionsHistoryService;


    @RequestMapping(value = "/transactionHistory.html", method = RequestMethod.GET)
    public String transactionsHistoryList(Model model){

        model.addAttribute("transactionsHistoryList", transactionsHistoryService.getAll(userService.getUsername()));

        return "transactionsHistory";
    }
}

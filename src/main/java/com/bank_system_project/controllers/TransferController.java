package com.bank_system_project.controllers;


import com.bank_system_project.models.Transfer;
import com.bank_system_project.services.TransactionsHistoryService;
import com.bank_system_project.services.TransferService;
import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class TransferController {

    @Autowired
    TransferService transferService;

    @Autowired
    TransactionsHistoryService transactionsHistoryService;

    @Autowired
    UserService userService;

//    @RequestMapping(value = "/transfers.html", method = RequestMethod.GET)
//    public String transfers(Model model){
//
//
//
//        return "transfers.html";
//    }

    @RequestMapping(value = "/transferForm.html", method = RequestMethod.GET)
    public String transferForm(Model model){

        model.addAttribute("transfer", new Transfer());

        return "transferForm.html";
    }

    @RequestMapping(value = "/transferForm.html", method = RequestMethod.POST)
    public String processTransferForm(@ModelAttribute("transfer") Transfer t){
        t.setExecutionDate(new Date());
        t.setRepeat(false);
        t.setStatus("Oczekujący");
        t.setUser(userService.getCurrentLoggedUser());
        transferService.save(t);
        return "redirect:/?transferSuccess";
    }

    @RequestMapping(value = "/transfers.html", method = RequestMethod.GET)
    public String transferList(Model model){

        model.addAttribute("transfersList", transferService.getCurrentLoggedUserTransfers(userService.getUsername()));

        return "transfers.html";
    }


}

package com.bank_system_project.controllers;


import com.bank_system_project.models.Transfer;
import com.bank_system_project.models.User;
import com.bank_system_project.models.UserDetails;
import com.bank_system_project.services.TransactionsHistoryService;
import com.bank_system_project.services.TransferService;
import com.bank_system_project.services.UserDetailsService;
import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Controller
public class TransferController {

    @Autowired
    TransferService transferService;

    @Autowired
    TransactionsHistoryService transactionsHistoryService;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping(value = "/transferForm.html", params = "repeat", method = RequestMethod.GET)
    public String transferRepeatForm(Model model, boolean repeat){

        model.addAttribute("transfer", new Transfer(repeat));

        return "transferForm.html";
    }


    @RequestMapping(value = "/transferForm.html", method = RequestMethod.POST)
    public String processTransferForm(@ModelAttribute("transfer") Transfer t){

        t.setExecutionDate(new Date());

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


    @RequestMapping(value = "/transfers.html", params = "sid", method = RequestMethod.GET)
    public String sendTransfer(Model model, long sid){


        Transfer transfer = transferService.getOne(sid);

        if (transfer.getAmount().compareTo(userService.getCurrentLoggedUser().getUserDetails().getMeans()) > 0){
            model.addAttribute("transfer", transfer);

            return "redirect:transfers.html?transferErr";
        }
        transfer.setStatus("Wysłany");
        UserDetails userDetails = userDetailsService.getOne(userService.getCurrentLoggedUser().getUserDetails().getId());
        userDetails.setMeans(userDetails.getMeans().subtract(transfer.getAmount()));
        userDetailsService.save(userDetails);

        transactionsHistoryService.save(transfer);


        transferService.save(transfer);


        return "redirect:/?transferSendSuccess";
    }
    @RequestMapping(value = "/transfers.html", params = "did", method = RequestMethod.GET)
    public String deleteTransfer(Model model, long did){

        transferService.delete(did);

        return "redirect:/transfers.html";
    }
    @RequestMapping(value = "/transfers.html", params = "eid", method = RequestMethod.GET)
    public String editTransfer(Model model, long eid){

        model.addAttribute("transfer", transferService.getOne(eid));

        return "transferForm.html";
    }


}

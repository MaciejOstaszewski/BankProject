package com.bank_system_project.controllers;


import com.bank_system_project.models.MobileNetwork;
import com.bank_system_project.models.TopUp;
import com.bank_system_project.models.Transfer;
import com.bank_system_project.services.TopUpService;
import com.bank_system_project.services.TransactionsHistoryService;
import com.bank_system_project.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TopUpController {


    @Autowired
    TopUpService topUpService;

    @Autowired
    TransactionsHistoryService transactionsHistoryService;

    @RequestMapping(value = "/topUpForm.html", method = RequestMethod.GET)
    public String topUpForm(Model model){

        model.addAttribute("topUp", new TopUp());

        return "topUpForm.html";
    }

    @RequestMapping(value = "/topUpForm.html", method = RequestMethod.POST)
    public String processTopUpForm(@ModelAttribute("topUp") TopUp t, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "topUpForm";
        }


        t.setExecutionDate(new Date());
//        topUpService.save(t);


        return "redirect:/?topUpSuccess";
    }

    @ModelAttribute("mobileNetworks")
    public List<MobileNetwork> loadMobileNetworks(){
        return topUpService.getAllMobileNetwork();
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        binder.registerCustomEditor(BigDecimal.class, "amount", new CustomNumberEditor(BigDecimal.class, numberFormat, false));

    }

}

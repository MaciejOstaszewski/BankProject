package com.bank_system_project.controllers;

import com.bank_system_project.models.CashFlowDate;
import org.springframework.format.datetime.joda.JodaTimeContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class cashFlowController {

    @RequestMapping(value = "/cashFlow.html", method = RequestMethod.GET)
    public String cashFlowDateForm(Model model){
        model.addAttribute("cashFlowDate", new CashFlowDate());

        return "cashFlow.html";
    }

    @RequestMapping(value = "/cashFlow.html", method = RequestMethod.POST)
    public String searchCashFlows(@ModelAttribute("cashFlowDate") CashFlowDate d){

         Date date = new Date();

        GregorianCalendar date1 = new GregorianCalendar(d.getYear(), d.getMonth(), 1);
        GregorianCalendar date2 = new GregorianCalendar(d.getYear(), d.getMonth()+1, 1);

        date = date1.getTime();

//        t.setExecutionDate(new Date());
//        topUpService.save(t);


        return "redirect:/cashFlow.html";
    }
}

/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.controllers;


import com.bank_system_project.models.CashFlowDate;
import com.bank_system_project.services.TransactionsHistoryService;
import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HistoryController {

    @Autowired
    UserService userService;

    @Autowired
    TransactionsHistoryService transactionsHistoryService;

    /**
     * Pobiera liste tranzakcji użytkownika
     * @param  model  przechowuje historie danego uzytkownika oraz pusty obiekt do formularza
     * @return  widok formularza na przedział czasowy oraz liste transakcjii
     */
    @RequestMapping(value = "/transactionHistory.html", method = RequestMethod.GET)
    public String transactionsHistoryList(Model model){

        model.addAttribute("transactionsHistoryList", transactionsHistoryService.getAll(userService.getUsername()));
        model.addAttribute("dates", new CashFlowDate());

        return "transactionsHistory";
    }

    /**
     * Pobiera liste transakcji użytkownika
     * @param  model  przechowuje historie danego uzytkownika z zadanego przedzialu czasowego
     * @param d data pobrana z formularza
     * @return lista transakcji z wybranego przedzialu czasowego
     * @return widok z informacją o błędzie
     */
    @RequestMapping(value = "/transactionsHistory.html", method = RequestMethod.POST)
    public String transactionsHistoryDates(Model model, @ModelAttribute("dates") CashFlowDate d){
        if (d.getDate1().getTime() > d.getDate2().getTime()){
            return "redirect:/transactionHistory.html?wrongDates";
        }

        model.addAttribute("transactionsHistoryList", transactionsHistoryService.getAllByTimeInterval(userService.getUsername(), d.getDate1(), d.getDate2()));

        return "transactionsHistory";
    }
}

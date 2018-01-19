/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.controllers;


import com.bank_system_project.models.Transfer;
import com.bank_system_project.models.TransferTemplate;
import com.bank_system_project.services.TransferTemplateService;
import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class TransferTemplateController {


    @Autowired
    UserService userService;

    @Autowired
    TransferTemplateService transferTemplateService;

    /**
     * Wyświetla formularz na szablon
     * @param model przechowuje pusty obiekt na szablon
     * @return strona z formularzem na szablon
     */
    @RequestMapping(value = "/transferTemplateForm.html", method = RequestMethod.GET)
    public String transferTemplateForm(Model model){

        model.addAttribute("transferTemplate", new TransferTemplate());

        return "transferTemplateForm";
    }

    /**
     * Zapisuje nowy szablon do przelewu
     * @param t szablon do zapisu
     * @return przkierowanie na strone z informacją o sukcesie
     */
    @RequestMapping(value = "/transferTemplateForm.html", method = RequestMethod.POST)
    public String processTransferTemplateForm(@ModelAttribute("transferTemplate") TransferTemplate t){


        t.setUser(userService.getCurrentLoggedUser());
        transferTemplateService.save(t);
        return "redirect:/?templateSuccess";
    }


    /**
     * Wyświetla strone z szablonami
     * @param model pusty obiekt na szablon
     * @return strona z szablonami
     */
    @RequestMapping(value = "/transferTemplates.html", method = RequestMethod.GET)
    public String transferTemplateList(Model model){
        model.addAttribute("TransferTemplate", new TransferTemplate());


        return "transferTemplates";
    }


    /**
     * Wyświetla formularz na przelew z danymi z szablonu
     * @param model przechowuje szablon
     * @param t wybrany szablon
     * @return strona z formularzem na przelew
     */
    @RequestMapping(value = "/transferTemplates.html", method = RequestMethod.POST)
    public String getTrensferFromTemplate(Model model, @ModelAttribute("TransferTemplate") TransferTemplate t){

        t = transferTemplateService.getOne(t.getTemplateId());

        Transfer transfer = new Transfer(false);
        transfer.setRecipientsAccount(t.getRecipientsAccount());
        transfer.setTitle(t.getTitle());
        transfer.setAmount(t.getAmount());
        transfer.setRecipientsNameAndAdress(t.getRecipientsNameAndAdress());


        model.addAttribute("transfer", transfer);

        return "transferForm.html";
    }

    /**
     * Pobiera liste szablonów
     * @return lista szablonów
     */
    @ModelAttribute("templates")
    public List<TransferTemplate> loadTemplates(){
        return transferTemplateService.getAllTransferTemplates(userService.getUsername());
    }

}

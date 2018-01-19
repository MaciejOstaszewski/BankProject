/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.controllers;


import com.bank_system_project.models.Messages;
import com.bank_system_project.models.User;
import com.bank_system_project.services.MessagesService;
import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class MessageController {

    @Autowired
    MessagesService messagesService;

    @Autowired
    UserService userService;


    /**
     * Formularz na nową wiadomość
     * @param model obiekt do formularza
     * @return strona z formularzem
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/messageForm.html", method = RequestMethod.GET)
    public String messageForm(Model model){

        model.addAttribute("message", new Messages());

        return "messageForm";
    }

    /**
     * Zapisuje wiadomość
     * @param m wiadomość pobrana z formularza
     * @return strona główna z komunikatem
     */
    @RequestMapping(value = "/messageForm.html", method = RequestMethod.POST)
    public String processMessageForm(@ModelAttribute("message") Messages m){
        if (userService.getUserByUsername(m.getUser().getUsername()) == null ) {
            return "redirect:messageForm.html?userNotExist";
        }

        User user = userService.getUserByUsername(m.getUser().getUsername());
        m.setUser(user);
        m.setSendDate(new Date());
        messagesService.save(m);


        return "redirect:/?newMessageSuccess";
    }


    /**
     * Wyświetla strone z wiadomościami
     * @param model przechowuje list wiadomości
     * @return strona z wiadomościami
     */
    @RequestMapping(value = "/messages.html", method = RequestMethod.GET)
    public String showMessages(Model model){

        model.addAttribute("messagesList", messagesService.getAllUserMessages(userService.getUsername()));

        return "messages";
    }


    /**
     * Wyświetla strone z treścią wiadomości
     * @param model przechowuje wiadomość
     * @param cid id wiadomości, której treść ma zostać wyświetlona
     * @return strona z treścią wiadomości
     */
    @RequestMapping(value = "/messages.html", params = "cid", method = RequestMethod.GET)
    public String showMessageContent(Model model, long cid){

        model.addAttribute("messageContent", messagesService.getMessage(cid));

        return "messageContent";
    }


    /**
     * Usuwa wiadomość
     * @param did id wiadomości, która ma zostać usunięta
     * @return przekierowuje na strone z wiadomościami
     */
    @RequestMapping(value = "/messages.html", params = "did", method = RequestMethod.GET)
    public String deleteMessagelong(long did){
        messagesService.delete(did);
        return "redirect:messages.html";
    }
}

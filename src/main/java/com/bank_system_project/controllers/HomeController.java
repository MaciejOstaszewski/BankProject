/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.controllers;

import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    /**
     * Wyświetla strone główną
     * @param model przechowuje stan konat użytkownika
     * @return strona główna
     */
    @RequestMapping(value = "/")
    public String showHomeSite(Model model) {
        if (!userService.getUsername().equals("admin")) {
            model.addAttribute("means", userService.getCurrentLoggedUser().getUserDetails().getMeans());
        }
        return "home";
    }



}

package com.bank_system_project.controllers;

import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String showHomeSite(Model model) {
        model.addAttribute("means", userService.getUserMeans());
        return "home";
    }
            // TEST TEST
}

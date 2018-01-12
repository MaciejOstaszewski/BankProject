package com.bank_system_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class homeController {


    @RequestMapping(value = "/")
    public String showHomeSite() {

        return "home";
    }

}

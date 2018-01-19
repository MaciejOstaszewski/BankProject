/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.controllers;


import com.bank_system_project.models.User;
import com.bank_system_project.models.UserDetails;
import com.bank_system_project.models.UserFormObject;
import com.bank_system_project.services.UserDetailsService;
import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;


@Controller
public class UserRegistrationFormController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Wyświetla formularz rejestracji
     * @param model przechowuje pusty obiekt użytkownika do formularza
     * @return strona z formularzem rejestracji
     */
    @GetMapping("/registrationForm.html")
    public String registration(Model model) {
        model.addAttribute("userFormObject", new UserFormObject());
        return "registrationForm";
    }


    /**
     * Uzupełnia dane i zapisuje użytkownika
     * @param userFormObject przechowuje dane użytkownika pobrane z formularza
     * @param bindingResult przechowuje błędy rejestracji
     * @return strona z formularzem rejestracji
     * @return strona z informacją o prawidłowej rejestracji
     */
    @PostMapping("/registrationForm.html")
    public String registration(@Valid @ModelAttribute("userFormObject") UserFormObject userFormObject, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            userFormObject.setPasswordConfirm(null);
            return "registrationForm";
        }

        UserDetails userDetails = new UserDetails();

        userDetails.setFirstname(userFormObject.getFirstname());
        userDetails.setAdress(userFormObject.getAdress());
        userDetails.setSurname(userFormObject.getSurname());
        userDetails.setEmail(userFormObject.getEmail());

        User user = new User();

        user.setPassword(userFormObject.getPassword());
        user.setUsername(userFormObject.getEmail());
        user.setUserDetails(userDetails);

        userDetailsService.fillAndSave(userDetails);
        userService.save(user);


        return "registrationSuccess";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("enabled", "roles");
    }
}

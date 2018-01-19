/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.controllers;


import com.bank_system_project.models.User;
import com.bank_system_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAcceptController {

    @Autowired
    UserService userService;


    @Secured("ROLE_ADMIN")
    @GetMapping(value = "userAccept.html")
    public String disabledUsersList(Model model){
        model.addAttribute("disabledUsers", userService.findDisabled());
        return "usersAccept";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "userAccept.html", params = {"eid"}, method = RequestMethod.GET)
    public String enableUser(long eid){
        User user = userService.getUserById(eid);
        user.setEnabled(true);
        userService.update(user);


        return "redirect:userAccept.html";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "userAccept.html", params = {"did"}, method = RequestMethod.GET)
    public String deleteUser(long did){

        userService.deleteUserById(did);

        return "redirect:userAccept.html";
    }
}

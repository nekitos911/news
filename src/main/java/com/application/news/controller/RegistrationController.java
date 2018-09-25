package com.application.news.controller;

import com.application.news.domain.User;
import com.application.news.domain.enums.Role;
import com.application.news.error.RegisterException;
import com.application.news.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    private final UserRepo userRepo;

    @Autowired
    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        try {
            if (userFromDb != null) {
                throw new RegisterException("user Exist", model);
            } else if(user.getUsername().length() < 3) {
                throw new RegisterException("username can not be less than 8", model);
            }
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));

            userRepo.save(user);
        } catch (RegisterException ex) {
            return "registration";
        }
        return "redirect:/login";
    }
}

package com.application.news.controller;

import com.application.news.domain.User;
import com.application.news.domain.enums.Role;
import com.application.news.repos.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/user")
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String userList(@RequestParam(required = false) String filter, Model model) {
        User user = userRepo.findByUsername(filter);
        if(user != null && !StringUtils.isEmpty(user.getUsername())) {
            model.addAttribute("users", Collections.singletonList(user));
        } else {
            model.addAttribute("users", userRepo.findAll());
        }
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form,
            @RequestParam String username
    ) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String role : form.keySet()) {
            if(roles.contains(role)) {
                user.getRoles().add(Role.valueOf(role));
            }
        }
        userRepo.save(user);
        return "redirect:/user";
    }
}

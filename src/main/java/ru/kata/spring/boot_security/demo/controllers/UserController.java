package ru.kata.spring.boot_security.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;




    @GetMapping()
    public String read(Principal principal, Model model){
        User user = userServiceImpl.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return  "user";
    }

}

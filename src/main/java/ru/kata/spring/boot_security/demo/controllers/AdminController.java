package ru.kata.spring.boot_security.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

        private final UserService userService;
        private final RoleRepository roleRepository;



        @GetMapping
        public String allUsers(Model model) {
            List<User> users = userService.findAll();
            model.addAttribute("users", users);
            return "user-list";
        }

        @GetMapping("/new")
        public String createUserForm(@ModelAttribute("user") User user, Model model) {
            model.addAttribute("roles", roleRepository.findAll());
            return "user-create";
        }

        @PostMapping
        public String createUser(@ModelAttribute("user") @Valid User user,
                                 BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "user-create";
            }

            userService.save(user);
            return "redirect:/admin";
        }

        @GetMapping("/edit")
        public String editUserForm(@RequestParam("id") Long id, Model model) {
            Optional<User> userById = userService.findById(id);
            model.addAttribute("roles", roleRepository.findAll());

            if (userById.isPresent()) {
                model.addAttribute("user", userById.get());
                return "edit-user";
            } else {
                return "redirect:/admin";
            }
        }

        @PostMapping("/edit")
        public String editUser(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "edit-user";
            }

            userService.updateUser(user);
            return "redirect:/admin";
        }

        @PostMapping("/delete")
        public String deleteUser(@RequestParam("id") Long id) {
            userService.deleteById(id);
            return "redirect:/admin";
        }
    }


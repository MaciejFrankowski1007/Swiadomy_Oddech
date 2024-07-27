package org.example.controller;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    private final UserDao dao;
    private final UserService userService;

    public UserController(UserDao dao, UserService userService) {
        this.dao = dao;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getUser(@PathVariable long id) {
        return Optional.ofNullable(dao.findById(id))
                .map(User::toString)
                .orElse("User not found");
    }

    @GetMapping("/{id}/delete")
    @ResponseBody
    public String deleteUser(@PathVariable long id) {
        dao.deleteUser(dao.findById(id));
        return "deleted";
    }

    @GetMapping("/{id}/updateUsername/{username}")
    @ResponseBody
    public String updateUser(@PathVariable long id, @PathVariable String username) {
        User user = dao.findById(id);
        user.setUsername(username);
        dao.updateUser(user);
        return user.toString();
    }

    @PostMapping("/register")
    public String register( @RequestParam String username, @RequestParam String password, Model model) {
        if (userService.register(username, password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "User is already exsist");
            return "register";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm(){
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.login(username, password)) {
            return "checkAirQuality";
        } else {
            model.addAttribute("error", "Wrong username or password!.");
            return "login";
        }
    }

}

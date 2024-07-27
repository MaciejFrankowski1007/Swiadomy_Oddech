package org.example.controller;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/userForm")
public class UserFormController {
    private final UserDao userDao;

    public UserFormController(UserDao userDao){
        this.userDao = userDao;
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("user", new User());
        return "user/form";
    }

    @GetMapping("/list")
    public String listAuthors(Model model) {
        model.addAttribute("authors", userDao.findAll());
        return "author/list";
    }

    @PostMapping("/form")
    public String saveForm(@Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "user/form";
        }
        if (user.getId() != null) {
            userDao.updateUser(user);
        } else {
            userDao.saveUser(user);
        }
        return "redirect:/userForm/list";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String confirmUser(@PathVariable long id, Model model) {
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        return "/user/confirmDelete";
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable long id){
        userDao.deleteUser(userDao.findById(id));
        return "redirect:/userForm/list";
    }

}

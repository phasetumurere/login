package com.unica.login.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping("/users/register")
    public String viewSignupForm(Model model, RedirectAttributes ra){
        model.addAttribute("pageTitle", "Register new User");
        model.addAttribute("user", new User());
        model.addAttribute("button","Signup");
        List<String> listRoles = Arrays.asList("Admin", "Client");
        model.addAttribute("listRoles",listRoles);
        return "sign_up";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra, Model model){
        service.saveUser(user);
        model.addAttribute("message","User "+user.getFirstname()+" Saved Successfully!!");
        return "saved_successfully";
    }

    @GetMapping("/users/viewAll")
    public String viewAll(Model model, RedirectAttributes ra){
        List<User> userList=service.viewAll();
        model.addAttribute("userList",userList);
        return "all_users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            User userById = service.findById(id);
            model.addAttribute("user", userById);
            List<String> listRoles = Arrays.asList("Admin", "Client");
            model.addAttribute("listRoles",listRoles);
            model.addAttribute("pageTitle", "Edit User (ID "+id+")");
            model.addAttribute("button", "Update");
            return "sign_up";
        } catch (UserNotFound e) {
            model.addAttribute("message", "User with ID "+id+ " is not available");
            return "all_users";
        }
    }
    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
           service.deleteById(id);
            ra.addFlashAttribute("message","Successful deleted");
        } catch (UserNotFound e) {
            ra.addFlashAttribute("message", "User with ID "+id+ " is not available");
        }
        return "redirect:/users/viewAll";
    }



}

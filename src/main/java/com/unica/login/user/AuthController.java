package com.unica.login.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        session.removeAttribute("message");
        model.addAttribute("title", "Users Management Login");
        model.addAttribute("userDto", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session) {
        session.removeAttribute("message");
        model.addAttribute("title", "User management Register");
        model.addAttribute("customerDto", new User());
        return "signup";
    }


    @PostMapping("/do_register")
    public String addCustomer(@ModelAttribute("customerDto") User user,
                              BindingResult result,
                              Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", user);
                return "signup";
            }
            String username = user.getEmail();
            org.springframework.security.core.userdetails.User customer = userService.findByUsername(username);

            if (customer != null) {
                model.addAttribute("customerDto", user);
                model.addAttribute("warning", "Your Email already registered ");
                System.out.println(username + "Already registered");
                return "signup";
            }
//            if (user.getPassword().equals(user.getRepeatPassword())) {
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//                model.addAttribute("customerDto", user);
//                userService.saveUser(user);
//                System.out.println(user.getEmail() + " registered");
//                model.addAttribute("message", "Registered Succesifully as " + user.getUsername());
//
//            } else {
//                model.addAttribute("customerDto", user);
//                System.out.println(user.getPassword() + " ⫩ " + user.getRepeatPassword());
//                model.addAttribute("warning", " Password not the same");
//                return "signup";
//            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server Error Try Again later");
            model.addAttribute("danger", "Kindly bear with us, It's Server error try again later!");

        }

        return "signup";
    }
}

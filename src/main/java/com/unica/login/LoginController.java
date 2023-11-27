package com.unica.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/users")
    public String LoginController(){
        return "index";
    }
}

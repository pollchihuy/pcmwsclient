package com.pollchihuy.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DefaultController {


    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("message", "Hello World");
        return "index";
    }

    @GetMapping("/2")
    public String index2(Model model,HttpServletRequest request){
        //db7d6b57-3e3b-4cb4-8151-7e5c1f4362cd
        System.out.println("Principal: " + request.getUserPrincipal());
        model.addAttribute("message", "Hello World");
        return "index2";
    }
}

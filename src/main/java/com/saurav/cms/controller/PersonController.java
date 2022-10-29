package com.saurav.cms.controller;

import com.saurav.cms.dto.person.LoginRequest;
import com.saurav.cms.dto.person.Register;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PersonController {

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("login", new LoginRequest());
        return "login";
    }
    @PostMapping(value = "/login")
    public RedirectView login(@ModelAttribute LoginRequest request, Model model){
        System.out.println("login request");
        System.out.println(request);
        return new RedirectView("/");
    }
    @PostMapping(value="/register")
    public RedirectView register(@ModelAttribute Register register, Model model) {
        return new RedirectView("/");
    }
}

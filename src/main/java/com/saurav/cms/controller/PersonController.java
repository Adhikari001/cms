package com.saurav.cms.controller;

import com.saurav.cms.dto.person.LoginRequest;
import com.saurav.cms.dto.person.Register;
import com.saurav.cms.service.user.MyUserDetailService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.saurav.cms.filter.LoginFilter.jwtTokenValue;

@Controller
public class PersonController {

    private final MyUserDetailService myUserDetailService;

    public PersonController(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "errorMessage", required = false) String errorMessage,HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("login", new LoginRequest());
        model.addAttribute("errorMessage", errorMessage);
        Cookie cookie = new Cookie(jwtTokenValue, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "login";
    }

    //username: saurav@saurav.com
    //password: Saurav@123
    @PostMapping(value = "/login")
    public String login(@Valid @ModelAttribute LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttrs){
        Pair<Boolean, String> loginResponse = myUserDetailService.loginRequest(loginRequest);
        //if login successful
        if(loginResponse.getFirst()){
            System.out.println("Login successful");
            response.addCookie(new Cookie(jwtTokenValue, loginResponse.getSecond()));
            return "redirect:/";
        }
        System.out.println("login failed " + loginResponse.getSecond());
        redirectAttrs.addAttribute("errorMessage", loginResponse.getSecond());
        return "redirect:/login";
    }
    @PostMapping(value="/register")
    public RedirectView register(@ModelAttribute Register register, Model model) {
        return new RedirectView("/");
    }
}

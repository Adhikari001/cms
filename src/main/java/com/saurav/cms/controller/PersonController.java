package com.saurav.cms.controller;

import com.saurav.cms.dto.person.InternalResponse;
import com.saurav.cms.dto.person.LoginRequest;
import com.saurav.cms.dto.person.UserDto;
import com.saurav.cms.service.user.MyUserDetailService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.Map;
import java.util.logging.Logger;

import static com.saurav.cms.filter.LoginFilter.jwtTokenValue;

@Controller
public class PersonController {

    private final MyUserDetailService myUserDetailService;

    private final Logger logger;

    public PersonController(MyUserDetailService myUserDetailService) {
        logger = Logger.getLogger(PersonController.class.getName());
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

    @GetMapping(value="/register")
    public String register(Model model) {

        logger.info("Get user register");
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,  BindingResult result,
                                      RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            logger.info("Error in user register" );
            return "register";
        }
        InternalResponse<String> registerResponse = myUserDetailService.registerNewUserAccount(userDto);
        if(registerResponse.getErrorMessage()!=null){
            redirectAttributes.addFlashAttribute("message", registerResponse.getErrorMessage());
            return "redirect:/register";
        }
        return "redirect:/";
    }
}

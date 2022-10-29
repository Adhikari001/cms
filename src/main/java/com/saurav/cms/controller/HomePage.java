package com.saurav.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.saurav.cms.dto.person.Register;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import static com.saurav.cms.filter.LoginFilter.jwtTokenValue;


@Slf4j
@Controller
public class HomePage {

    @GetMapping(value = "/")
    public String homePage(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "deleteCookie", defaultValue = "false") String deleteCookie,
                           Model model){
        String newToken;
        if (Boolean.parseBoolean(deleteCookie)) {
            newToken = null;
        }else {
            newToken =  LocalDateTime.now().toString();
        }
        log.info("Inserting new cookie {}", newToken);
        response.addCookie(new Cookie(jwtTokenValue, newToken));
        model.addAttribute("loggedIn", newToken!=null);
        return "index";
    }
    
}

package com.saurav.cms.filter;

import com.saurav.cms.util.HelperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class LoginFilter implements Filter {
    public static final String jwtTokenValue = "jwtToken";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Optional<String> jwtToken = HelperUtil.readServletCookie(request.getCookies(), jwtTokenValue);
        if(jwtToken.isPresent()){

        }
        chain.doFilter(request, response);
    }
}

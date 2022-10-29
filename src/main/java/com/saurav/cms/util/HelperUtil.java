package com.saurav.cms.util;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

public class HelperUtil {
    public static Optional<String> readServletCookie(Cookie[] cookies, String name){
        if(cookies==null || cookies.length == 0)
            return Optional.empty();
        return Arrays.stream(cookies)
                .filter(cookie->name.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }
}

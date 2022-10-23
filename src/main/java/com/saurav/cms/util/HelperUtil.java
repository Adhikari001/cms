package com.saurav.cms.util;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

public class HelperUtil {
    public static Optional<String> readServletCookie(Cookie[] cookies, String name){
        return Arrays.stream(cookies)
                .filter(cookie->name.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }
}

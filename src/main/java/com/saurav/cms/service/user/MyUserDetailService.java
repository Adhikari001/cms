package com.saurav.cms.service.user;

import com.saurav.cms.dto.person.LoginRequest;
import org.springframework.data.util.Pair;

public interface MyUserDetailService {
    //if the response is true, then the username and password is correct and the user can be redirected to home page
    //else redirect to login screen with use
    Pair<Boolean, String> loginRequest(LoginRequest request);
}

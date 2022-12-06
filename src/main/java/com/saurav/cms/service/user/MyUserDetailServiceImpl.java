package com.saurav.cms.service.user;

import com.saurav.cms.comms.security.JWTTokenUtil;
import com.saurav.cms.comms.security.PasswordEncoder;
import com.saurav.cms.dto.person.LoginRequest;
import com.saurav.cms.entity.person.MyUser;
import com.saurav.cms.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailServiceImpl implements MyUserDetailService, UserDetailsService {
    private final MyUserRepository myUserRepository;
    private final JWTTokenUtil jwtTokenUtil;
    
    @Autowired
    public MyUserDetailServiceImpl(MyUserRepository myUserRepository,
                                   JWTTokenUtil jwtTokenUtil) {
        this.myUserRepository = myUserRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> optionalPerson = myUserRepository.findPersonByEmail(username);
        if(optionalPerson.isEmpty()){
            throw new UsernameNotFoundException("Can not find user from the provided username :: " + username);
        }
        MyUser myUser = optionalPerson.get();
        return User.withUsername(myUser.getEmail()).password(myUser.getPassword()).authorities("USER").passwordEncoder().build();
    }
    @Override
    public Pair<Boolean, String> loginRequest(LoginRequest request) {
        Optional<MyUser> optionalPerson = myUserRepository.findPersonByEmail(request.getEmail());
        if(optionalPerson.isEmpty()){
            return Pair.of(false, "Can not find user from the provided email");
        }
        MyUser myUser = optionalPerson.get();
        if (!PasswordEncoder.verifyUserPassword(request.getPassword(), myUser.getPassword(), myUser.getSalt())) {
            return Pair.of(false, "Password does not match.");
        }
        String jwt = jwtTokenUtil.createJWT(myUser.getEmail(), null, myUser.getRole().getName());
        return Pair.of(true, jwt);
    }
}

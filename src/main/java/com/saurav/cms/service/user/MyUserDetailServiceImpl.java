package com.saurav.cms.service.user;

import com.saurav.cms.comms.security.JWTTokenUtil;
import com.saurav.cms.comms.security.PasswordEncoder;
import com.saurav.cms.dto.person.InternalResponse;
import com.saurav.cms.dto.person.LoginRequest;
import com.saurav.cms.dto.person.UserDto;
import com.saurav.cms.entity.person.MyUser;
import com.saurav.cms.repository.MyUserRepository;
import com.saurav.cms.service.rolepermission.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailServiceImpl implements MyUserDetailService, UserDetailsService {
    private final MyUserRepository myUserRepository;
    private final JWTTokenUtil jwtTokenUtil;

    private final RoleService roleService;
    
    @Autowired
    public MyUserDetailServiceImpl(MyUserRepository myUserRepository,
                                   JWTTokenUtil jwtTokenUtil, RoleService roleService) {
        this.myUserRepository = myUserRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.roleService = roleService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = myUserRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Can not find user from the provided username :: " + username);
        }
        return new MyUserPrincipal(user);
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

    @Override
    public InternalResponse<String> registerNewUserAccount(UserDto userDto) {
        if (emailExists(userDto.getEmail())) {
            return new InternalResponse<>("There is an account with that email address: " + userDto.getEmail());
        }
        MyUser user = new MyUser();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(user.getEmail());
        user.setRole(roleService.getRoleById(1L));
        return new InternalResponse<>(null, "");
    }

    private boolean emailExists(String email) {
        return myUserRepository.findByEmail(email) != null;
    }

}

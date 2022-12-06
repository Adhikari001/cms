package com.saurav.cms.dto.person;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "email can not be blank")
    private String email;
    @NotBlank(message = "password can not be blank")
    private String password;
}

package com.saurav.cms.dto.person;

import com.saurav.cms.comms.validator.PasswordMatches;
import com.saurav.cms.comms.validator.ValidEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@PasswordMatches
public class UserDto {
    @NotNull(message = "First name can not be empty")
    @NotEmpty(message = "First name can not be empty")
    private String firstName;

    @NotNull(message = "Last name can not be empty")
    @NotEmpty(message = "Last name can not be empty")
    private String lastName;

    @NotNull(message = "Password can not be empty")
    @NotEmpty(message = "Password can not be empty")
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    // standard getters and setters
}
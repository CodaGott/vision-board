package com.visionboard.data.dto.loginNlogout;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLogin {

    @NotBlank(message = "email field can't be blank")
    @Size(min = 3, max = 60)
    private String email;
    @NotBlank(message = "Password field can't be empty")
    @Size(min = 8, max = 30)
    private String password;
}

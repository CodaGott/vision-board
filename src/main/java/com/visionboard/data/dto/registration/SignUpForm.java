package com.visionboard.data.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {

    @NotBlank(message = "Name can't be empty")
    @Size(min = 5, max = 40)
    private String name;

    @NotBlank(message = "Email can't empty")
    @Size(max = 70)
    @Email
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 8, message = "Message must be up to 8 characters")
    private String password;
}

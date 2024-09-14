package com.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {

    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Size(min = 4, message = "Username should have at least 4 characters")
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
}

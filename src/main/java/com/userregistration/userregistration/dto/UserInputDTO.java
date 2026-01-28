package com.userregistration.userregistration.dto;

import com.userregistration.userregistration.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserInputDTO {

    @Size(min = 5, max = 12, message = "Username must have between 5 and 12 characters")
    @NotBlank(message = "Required field")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Required field")
    private String email;

    @Size(min = 8, max = 72, message = "Password must have between 8 and 72 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^\\w\\s]).+$",
            message = "Password must have upper, lower, number and symbol"
    )
    @NotBlank(message = "Required field")
    private String password;



    public UserInputDTO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

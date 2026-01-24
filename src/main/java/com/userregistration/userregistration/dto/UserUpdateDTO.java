package com.userregistration.userregistration.dto;

import com.userregistration.userregistration.entities.User;
import jakarta.validation.constraints.*;

public class UserUpdateDTO {

    @Size(min = 5, max = 12, message = "Username must have between 5 and 12 characters")
    @NotBlank(message = "Required field")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Required field")
    private String email;

    private String password;

    @AssertTrue(message = "Password must have between 8 and 72 characters and contain upper, lower, number and symbol")
    public boolean isPasswordValid() {
        if (password == null || password.isBlank()) {
            return true; // não mandou senha, ok
        }
        if (password.length() < 8 || password.length() > 72) {
            return false;
        }
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^\\w\\s]).+$");
    }



    public UserUpdateDTO() {
    }

    public UserUpdateDTO(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public UserUpdateDTO(User user) {
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
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

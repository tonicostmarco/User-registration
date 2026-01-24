package com.userregistration.userregistration.dto;

import com.userregistration.userregistration.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {

    @Size(min = 5, max = 12, message = "O usuario deve ter de 5 a 12 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;

    @Size(min = 8, max = 72, message = "A senha deve entre 8 e 72 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^\\w\\s]).+$",
            message = "Password must have upper, lower, number and symbol"
    )
    private String password;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserUpdateDTO(User user) {
        name = user.getName();
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
}

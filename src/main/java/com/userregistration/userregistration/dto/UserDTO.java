package com.userregistration.userregistration.dto;

import com.userregistration.userregistration.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    private Long id;

    @Size(min = 5, max = 12, message = "Username must have between 5 and 12 characters")
    @NotBlank(message = "Required field")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Required field")
    private String email;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDTO(User user) {
     id = user.getId();
     name = user.getName();
     email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

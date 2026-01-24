package com.aquecimento.aquecimento.dto;

import com.aquecimento.aquecimento.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    private Long id;

    @Size(min = 5, max = 12, message = "O usuario deve ter de 5 a 12 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;


    public UserDTO() {
    }

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDTO(User user) {
     id = user.getId();
     name = user.getName();
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
}

package com.prueba.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String name,
        @Email(message = "El formato de email no es válido")
        @NotBlank
        String email,
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        @NotBlank
        String password,
        List<PhoneDto> phones

) {
}

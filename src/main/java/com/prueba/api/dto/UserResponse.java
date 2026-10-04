package com.prueba.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.LongToIntFunction;

// Representa el JSON que devolvemos al crear el usuario.
public record UserResponse(
        Long id,
        LocalDateTime created,
        LocalDateTime modified,

        // @JsonProperty fuerza el nombre del campo en el JSON.
        // En Java usamos camelCase (lastLogin), pero el enunciado pide "last_login"
        @JsonProperty("last_login") LocalDateTime lastLongin,

        String token,
        @JsonProperty("isactive") boolean isActive,

        String name,
        String email,
        List<PhoneDto> phones
) {
}

package com.prueba.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // la BD genera el id (autoincremental)
    private Long id;              // lo generamos nosotros en el service con UUID.randomUUID()
    private String name;

    @Column(unique = true, nullable = false)   // refleja la restricción UNIQUE de la tabla
    private String email;

    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;   // JPA lo mapea solo a la columna last_login
    private String token;
    private boolean active;            // en la tabla la columna se llama "active"

    // Un usuario tiene muchos teléfonos.
    // cascade = ALL: al guardar el usuario, se guardan también sus teléfonos.
    // orphanRemoval: si se saca un teléfono de la lista, se borra de la BD.
    // @JoinColumn: la columna user_id de "phones" es la clave foránea.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private List<Phone> phones = new ArrayList<>();

    // Getters y setters
}
package com.prueba.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
// le dice a JPA que esta clase se mapea a una tabla
@Entity
@Table(name = "phones")       // nombre de la tabla en la BD
public class Phone {
    @Id                                                   // clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // la BD genera el id (autoincremental)
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;
}
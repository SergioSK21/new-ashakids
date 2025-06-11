package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    private String nombre;
    private String correo;
    private String contraseña;
    private String telefono;

    @Column(unique = true)
    private String codigo; // Código ASHA, por ejemplo "u23428", "t32473"

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        padre, terapeuta, admin
    }
}

package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "El correo debe tener un formato válido.")
    @Size(max = 100)
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres.")
    private String contraseña;

    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres.")
    private String telefono;

    @NotBlank(message = "El código ASHA es obligatorio.")
    @Size(max = 20, message = "El código ASHA no puede exceder 20 caracteres.")
    @Column(unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        padre, terapeuta, admin
    }
}

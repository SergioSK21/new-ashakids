package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "terapeutas")
@Getter
@Setter
public class Terapeuta {

    @Id
    private Long id; // Debe coincidir con id_usuario, no es autogenerado

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El servicio es obligatorio.")
    @Size(max = 100)
    private String servicio;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora; // No parece ir aquí desde lo lógico del modelo

    @NotBlank(message = "El correo es obligatorio.")
    @Email
    private String correo;

    private Double monto; // Validación puede ir con @Min si lo deseas
}

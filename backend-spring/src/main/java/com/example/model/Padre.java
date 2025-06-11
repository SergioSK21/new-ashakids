package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "padres")
@Getter
@Setter
public class Padre {

    @Id
    private Integer id_padre;

    @Size(max = 255, message = "La dirección no puede exceder los 255 caracteres.")
    private String direccion;

    @OneToOne
    @JoinColumn(name = "id_padre")
    @MapsId
    private Usuario usuario;
}

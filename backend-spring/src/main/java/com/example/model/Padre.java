package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "padres")
@Getter
@Setter
public class Padre {

    @Id
    private Integer id_padre;  // Mismo que id_usuario

    private String direccion;

    @OneToOne
    @JoinColumn(name = "id_padre") // se une con usuarios.id_usuario
    @MapsId
    private Usuario usuario;
}

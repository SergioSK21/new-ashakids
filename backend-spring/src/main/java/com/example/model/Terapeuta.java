package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "terapeutas")
@Getter
@Setter
public class Terapeuta {

    @Id
    private Long id;
    
    private String nombre;
    private String servicio;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    private String correo;
    private Double monto;

}

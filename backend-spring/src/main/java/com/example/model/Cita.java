package com.example.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cita;

    @Column(name = "id_nino")
    private Integer idNino;

    @ManyToOne
    @JoinColumn(name = "id_nino", insertable = false, updatable = false)
    private Nino nino;

    @Column(name = "id_terapeuta")
    private Integer idTerapeuta;

    @ManyToOne
    @JoinColumn(name = "id_terapeuta", insertable = false, updatable = false)
    private Terapeuta terapeuta;

    private LocalDate fecha;

    private LocalTime hora;

    private String modalidad;

    private String enlace_o_direccion;

    private String estado = "programada";

    private String retroalimentacion_padre;
}

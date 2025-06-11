package com.example.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Debe estar asociada a un niño.")
    @Column(name = "id_nino")
    private Integer idNino;

    @ManyToOne
    @JoinColumn(name = "id_nino", insertable = false, updatable = false)
    private Nino nino;

    @NotNull(message = "Debe estar asociada a un terapeuta.")
    @Column(name = "id_terapeuta")
    private Integer idTerapeuta;

    @ManyToOne
    @JoinColumn(name = "id_terapeuta", insertable = false, updatable = false)
    private Terapeuta terapeuta;

    @NotNull(message = "La fecha es obligatoria.")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria.")
    private LocalTime hora;

    @NotBlank(message = "Debe indicar si es presencial o virtual.")
    @Size(max = 20)
    private String modalidad;

    @NotBlank(message = "Debe proporcionar un enlace o dirección.")
    @Size(max = 255)
    private String enlace_o_direccion;

    @Size(max = 20)
    private String estado = "programada";

    private String retroalimentacion_padre;
}

package com.example.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ninos")
@Getter
@Setter
public class Nino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_nino;

    private String nombre;

    private LocalDate fechaNacimiento;

    @Column(name = "id_padre")
    private Integer idPadre;

}

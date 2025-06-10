package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findByIdNino(Integer idNino);
    
    List<Cita> findByIdTerapeuta(Integer id_terapeuta);

}
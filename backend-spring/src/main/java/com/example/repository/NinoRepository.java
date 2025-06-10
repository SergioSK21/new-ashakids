package com.example.repository;

import com.example.model.Nino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NinoRepository extends JpaRepository<Nino, Integer> {
    List<Nino> findByIdPadre(Integer idPadre);
}

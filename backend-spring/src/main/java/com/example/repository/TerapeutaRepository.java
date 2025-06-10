package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Terapeuta;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {
}

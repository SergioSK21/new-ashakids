package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Padre;

public interface PadreRepository extends JpaRepository<Padre, Integer> {
}

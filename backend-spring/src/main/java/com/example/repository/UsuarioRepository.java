package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCodigoAndContraseña(String codigo, String contraseña);
    Usuario findByCodigo(String codigo);
}

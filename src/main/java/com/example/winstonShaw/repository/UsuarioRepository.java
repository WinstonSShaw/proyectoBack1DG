package com.example.winstonShaw.repository;

import com.example.winstonShaw.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUserName(String userName);
}

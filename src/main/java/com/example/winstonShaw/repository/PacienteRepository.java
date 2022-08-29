package com.example.winstonShaw.repository;

import com.example.winstonShaw.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}

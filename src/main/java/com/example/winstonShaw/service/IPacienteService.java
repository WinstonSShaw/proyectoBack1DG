package com.example.winstonShaw.service;

import com.example.winstonShaw.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    List<Paciente> listarPacientes();

    Paciente guardarPaciente(Paciente paciente);

    Optional<Paciente> buscarPacienteXId(Long id);

    void eliminarPaciente(Long id);

    Paciente actualizarPaciente(Paciente paciente);
 }

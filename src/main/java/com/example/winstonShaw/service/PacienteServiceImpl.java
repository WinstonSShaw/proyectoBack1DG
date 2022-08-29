package com.example.winstonShaw.service;

import com.example.winstonShaw.entities.Paciente;
import com.example.winstonShaw.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPacienteXId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}

package com.example.winstonShaw.service;

import com.example.winstonShaw.entities.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    List<Turno> listarTurnos();

    Turno guardarTurno(Turno turno);

    Optional<Turno> buscarTurnoXId(Long id);

    void eliminarTurno(Long id);

    Turno actualizarTurno(Turno turno);
}

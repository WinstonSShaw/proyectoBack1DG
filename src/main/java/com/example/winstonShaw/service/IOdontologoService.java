package com.example.winstonShaw.service;

import com.example.winstonShaw.entities.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    List<Odontologo> listarOdontologo();

    Odontologo guardarOdontologo(Odontologo odontologo);

    Optional<Odontologo> buscarOdontologoXId(Long id);

    void eliminarOdontologo(Long id);

    Odontologo actualizarOdontologo(Odontologo odontologo);

}

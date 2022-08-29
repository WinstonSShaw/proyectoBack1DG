package com.example.winstonShaw.service;

import com.example.winstonShaw.entities.Odontologo;
import com.example.winstonShaw.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements IOdontologoService{
    @Autowired
    private OdontologoRepository odontologoRepository;


    @Override
    public List<Odontologo> listarOdontologo() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarOdontologoXId(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }
}

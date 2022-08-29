package com.example.winstonShaw;

import com.example.winstonShaw.entities.Odontologo;
import com.example.winstonShaw.service.IOdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    IOdontologoService odontologoService;

    @Test
    @Order(1)
    public void agregarOdontologoTest(){
        Odontologo odontologoDePrueba = new Odontologo("Juan", "Bermudez", 654);
        odontologoService.guardarOdontologo(odontologoDePrueba);

        Optional<Odontologo> odontologoDePruebaRecuperado = odontologoService.buscarOdontologoXId(1l);
        assertTrue(odontologoDePruebaRecuperado.isPresent());
    }

    @Test
    @Order(2)
    public void buscarOdontologoTest(){
        Long idBuscado = 1l;
        Optional<Odontologo> odontologoDePruebaRecuperado = odontologoService.buscarOdontologoXId(idBuscado);
        assertTrue(odontologoDePruebaRecuperado.isPresent());
    }

    @Test
    @Order(3)
    public void listarOdontologosTest(){
        List<Odontologo> listaDePrueba = odontologoService.listarOdontologo();
        assertTrue(listaDePrueba.size() > 0);
    }

    @Test
    @Order(4)
    public void actualizarOdontologoTest(){
        Long idBuscado = 1l;
        Odontologo odontologoParaActualizar = new Odontologo(idBuscado, "Pablo", "Puertas", 123);
        odontologoService.actualizarOdontologo(odontologoParaActualizar);
        Optional<Odontologo> odontologoRecuperado = odontologoService.buscarOdontologoXId(idBuscado);
        assertEquals("Puertas", odontologoRecuperado.get().getApellido());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest(){
        Long idBuscado = 1l;
        odontologoService.eliminarOdontologo(idBuscado);
        List<Odontologo> listaDePrueba = odontologoService.listarOdontologo();
        assertTrue(listaDePrueba.size() == 0);
    }
}

package com.example.winstonShaw;

import com.example.winstonShaw.entities.Domicilio;
import com.example.winstonShaw.entities.Odontologo;
import com.example.winstonShaw.entities.Paciente;
import com.example.winstonShaw.entities.Turno;
import com.example.winstonShaw.service.IOdontologoService;
import com.example.winstonShaw.service.IPacienteService;
import com.example.winstonShaw.service.ITurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {
    @Autowired
    ITurnoService turnoService;
    @Autowired
    IOdontologoService odontologoService;
    @Autowired
    IPacienteService pacienteService;

    @Test
    @Order(1)
    public void agregarTurnoTest(){
        Odontologo odontologoDePrueba = new Odontologo("Juan", "Bermudez", 654);
        odontologoService.guardarOdontologo(odontologoDePrueba);
        Domicilio domicilioDePrueba = new Domicilio("Rivadavia", 741, "Cordoba", "Cordoba");
        Paciente pacienteDePrueba = new Paciente("Cosma", "Agustin", 123465, LocalDate.now(), domicilioDePrueba);
        pacienteService.guardarPaciente(pacienteDePrueba);

        //Odontologo odontologoConId = new Odontologo(1l);
        //Paciente pacienteConId = new Paciente(1l);
        Turno turnoDePrueba = new Turno(odontologoService.buscarOdontologoXId(1l).get(), pacienteService.buscarPacienteXId(1l).get(), LocalDate.now());
        turnoService.guardarTurno(turnoDePrueba);

        Optional<Turno> turnoDePruebaRecuperado = turnoService.buscarTurnoXId(1l);
        assertTrue(turnoDePruebaRecuperado.isPresent());

    }

    @Test
    @Order(2)
    public void buscarTurnoTest(){
        Long idBuscado = 1l;
        Optional<Turno> turnoDePruebaRecuperado = turnoService.buscarTurnoXId(idBuscado);
        assertTrue(turnoDePruebaRecuperado.isPresent());
    }

    @Test
    @Order(3)
    public void listarTurnosTest(){
        List<Turno> listaDePrueba = turnoService.listarTurnos();
        assertTrue(listaDePrueba.size() > 0);
    }

    @Test
    @Order(4)
    public void actualizarTurnoTest(){
        Long idBuscado = 1l;
        Odontologo odontologoParaActualizar = new Odontologo(idBuscado,"Jero", "Monasterolo", 321);
        odontologoService.actualizarOdontologo(odontologoParaActualizar);
        Domicilio domicilioParaActualizar = new Domicilio("Sucre", 528, "Cordoba", "Cordoba");
        Paciente pacienteParaActualizar = new Paciente(idBuscado,"Cheli", "Carlos", 74115, LocalDate.now(), domicilioParaActualizar);
        pacienteService.actualizarPaciente(pacienteParaActualizar);

        Turno turnoParaActualizar = new Turno(idBuscado,odontologoService.buscarOdontologoXId(1l).get(), pacienteService.buscarPacienteXId(1l).get(), LocalDate.now());
        turnoService.actualizarTurno(turnoParaActualizar);

        Optional<Turno> turnoRecuperado = turnoService.buscarTurnoXId(idBuscado);
        assertEquals("Cheli", turnoRecuperado.get().getPaciente().getApellido());
    }

    @Test
    @Order(5)
    public void eliminarTurnoTest(){
        Long idBuscado = 1l;
        turnoService.eliminarTurno(idBuscado);
        List<Turno> listaDePrueba = turnoService.listarTurnos();
        assertTrue(listaDePrueba.size() == 0);
    }

}

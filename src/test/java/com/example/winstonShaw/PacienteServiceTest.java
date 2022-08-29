package com.example.winstonShaw;

import com.example.winstonShaw.entities.Domicilio;
import com.example.winstonShaw.entities.Paciente;
import com.example.winstonShaw.service.IPacienteService;
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
public class PacienteServiceTest {
    @Autowired
    IPacienteService pacienteService;

    @Test
    @Order(1)
    public void agregarPacienteTest(){
        Domicilio domicilioDePrueba = new Domicilio("Rivadavia", 741, "Cordoba", "Cordoba");
        Paciente pacienteDePrueba = new Paciente("Cosma", "Agustin", 123465, LocalDate.now(), domicilioDePrueba);
        pacienteService.guardarPaciente(pacienteDePrueba);

        Optional<Paciente> pacienteDePruebaRecuperado = pacienteService.buscarPacienteXId(1l);
        assertTrue(pacienteDePruebaRecuperado.isPresent());
    }

    @Test
    @Order(2)
    public void buscarPacienteTest(){
        Long idBuscado = 1l;
        Optional<Paciente> pacienteDePruebaRecuperado = pacienteService.buscarPacienteXId(idBuscado);
        assertTrue(pacienteDePruebaRecuperado.isPresent());
    }

    @Test
    @Order(3)
    public void listarPacientesTest(){
        List<Paciente> listaDePrueba = pacienteService.listarPacientes();
        assertTrue(listaDePrueba.size() > 0);
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Long idBuscado = 1l;
        Domicilio domicilioParaActualizar = new Domicilio("Fader", 3654, "Cba", "Cba");
        Paciente pacienteParaActualizar = new Paciente(idBuscado, "Toledo", "Nicolas", 963258, LocalDate.now(), domicilioParaActualizar);
        pacienteService.actualizarPaciente(pacienteParaActualizar);
        Optional<Paciente> pacienteRecuperado = pacienteService.buscarPacienteXId(idBuscado);
        assertEquals("Toledo", pacienteRecuperado.get().getApellido());
    }

    @Test
    @Order(5)
    public void eliminarPacienteTest(){
        Long idBuscado = 1l;
        pacienteService.eliminarPaciente(idBuscado);
        List<Paciente> listaDePrueba = pacienteService.listarPacientes();
        assertTrue(listaDePrueba.size() == 0);
    }
}

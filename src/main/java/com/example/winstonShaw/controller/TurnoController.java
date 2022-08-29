package com.example.winstonShaw.controller;

import com.example.winstonShaw.entities.Odontologo;
import com.example.winstonShaw.entities.Paciente;
import com.example.winstonShaw.entities.Turno;
import com.example.winstonShaw.exceptions.ResourceNotFoundException;
import com.example.winstonShaw.service.IOdontologoService;
import com.example.winstonShaw.service.IPacienteService;
import com.example.winstonShaw.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IOdontologoService odontologoService;

    public static final Logger logger=Logger.getLogger(TurnoController.class);


    @GetMapping
    public ResponseEntity<List<Turno>> traerTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoXId(id);
        if(turnoBuscado.isPresent()){
            logger.info("Se encontro el turno con id: " + id);
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            logger.error("Error: No se encontro el turno con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteXId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoXId(turno.getOdontologo().getId());

        if(pacienteBuscado.isPresent()){
            if(odontologoBuscado.isPresent()){
                logger.info("Se guardo el turno con id:" + turno.getId()+ ", pacienteId:" + turno.getPaciente().getId()+ ", odontologoId:" + turno.getOdontologo().getId());
                return ResponseEntity.ok(turnoService.guardarTurno(turno));
            }
            else {
                logger.error("No se guardo el turno pq no se encontro el odontologo con id:" + turno.getOdontologo().getId());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        else{
            logger.error("No se guardo el turno pq no se encontro el paciente con id:" + turno.getPaciente().getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        Optional<Turno> turnoBuscadoParaActualizar = turnoService.buscarTurnoXId(turno.getId());
        Optional<Paciente> pacienteBuscadoParaActualizar = pacienteService.buscarPacienteXId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscadoParaActualizar = odontologoService.buscarOdontologoXId(turno.getOdontologo().getId());

        if(turnoBuscadoParaActualizar.isPresent()){
            if(pacienteBuscadoParaActualizar.isPresent()){
                if(odontologoBuscadoParaActualizar.isPresent()){
                    logger.info("Se actualizo el turno con id:" + turno.getId()+ ", pacienteId:" + turno.getPaciente().getId()+ ", odontologoId:" + turno.getOdontologo().getId());
                    return ResponseEntity.ok(turnoService.actualizarTurno(turno));
                }
                else {
                    logger.error("No se actualizo el turno pq no se encontro el odontologo con id:" + turno.getOdontologo().getId());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }
            else{
                logger.error("No se actualizo el turno pq no se encontro el paciente con id:" + turno.getPaciente().getId());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        else {
            logger.error("No se actualizo el turno pq no existe el turno con id:" + turno.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoXId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            logger.info("Se borro el turno con id:" + id);
            return ResponseEntity.ok("Se elimino el turno con id= " + id);
        }
        else {
            logger.error("Error: No se encontro el turno con id: " + id);
            throw new ResourceNotFoundException("Error: No se pudo eliminar el turno con id= " + id + " porque no existe.");
        }
    }
}

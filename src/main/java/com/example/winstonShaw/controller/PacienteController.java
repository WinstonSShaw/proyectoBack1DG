package com.example.winstonShaw.controller;

import com.example.winstonShaw.entities.Paciente;
import com.example.winstonShaw.exceptions.ResourceNotFoundException;
import com.example.winstonShaw.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private IPacienteService pacienteService;

    public static final Logger logger=Logger.getLogger(PacienteController.class);

    @GetMapping
    public ResponseEntity<List<Paciente>> traerPacientes(){
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteXId(id);
        if (pacienteBuscado.isPresent()){
            logger.info("Se encontro el paciente con id: " + id);
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            logger.error("Error: No se encontro el paciente con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente){
        logger.info("Se guardo el paciente con id:"+paciente.getId() + ", nombre: " +paciente.getNombre()+" "+paciente.getApellido()+ ", dni:"+paciente.getDni()
        + ", domicilio: id:" + paciente.getDomicilio().getId() + ", calle: " + paciente.getDomicilio().getCalle() + ", numero:" + paciente.getDomicilio().getNumero()
        + ", localidad: " + paciente.getDomicilio().getLocalidad() + ", provincia: " + paciente.getDomicilio().getProvincia());
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteParaActualizar = pacienteService.buscarPacienteXId(paciente.getId());
        if (pacienteParaActualizar.isPresent()){
            paciente.getDomicilio().setId(pacienteParaActualizar.get().getDomicilio().getId());
            logger.info("Se modifico el paciente con id:"+paciente.getId() + ", datos guardados: nombre: " +paciente.getNombre()+" "+paciente.getApellido()+ ", dni:"+paciente.getDni()
            + ", domicilio: id:" + paciente.getDomicilio().getId() + ", calle: " + paciente.getDomicilio().getCalle() + ", numero:" + paciente.getDomicilio().getNumero()
                    + ", localidad: " + paciente.getDomicilio().getLocalidad() + ", provincia: " + paciente.getDomicilio().getProvincia());
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        }
        else {
            logger.error("Error: No se encontro el paciente con id: " + paciente.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteXId(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            logger.info("Se elimino al paciente con id:"+ id);
            return ResponseEntity.ok("Se elimino al paciente con id= " + id);
        }
        else {
            logger.error("Error: No se encontro el paciente con id: " + id);
            throw new ResourceNotFoundException("Error: No se pudo eliminar al paciente con id= " + id + " porque no existe.");
        }
    }
}

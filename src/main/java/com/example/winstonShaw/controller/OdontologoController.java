package com.example.winstonShaw.controller;

import com.example.winstonShaw.entities.Odontologo;
import com.example.winstonShaw.exceptions.ResourceNotFoundException;
import com.example.winstonShaw.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private IOdontologoService odontologoService;

    public static final Logger logger=Logger.getLogger(OdontologoController.class);

    @GetMapping
    public ResponseEntity<List<Odontologo>> traerOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontolgo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoXId(id);
        if(odontologoBuscado.isPresent()){
            logger.info("Se encontro el odontologo con id: " + id);
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            logger.error("Error: No se encontro el odontologo con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarNuevoOdontologo(@RequestBody Odontologo odontologo){
        logger.info("Se guardo el odontologo con id:"+odontologo.getId() + ", nombre: " +odontologo.getNombre()+" "+odontologo.getApellido()+ ", matricula:"+odontologo.getMatricula());
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoParaActualizar = odontologoService.buscarOdontologoXId(odontologo.getId());
        if (odontologoParaActualizar.isPresent()){
            logger.info("Se modifico el odontologo con id:"+odontologo.getId() + ", datos guardados: nombre: " +odontologo.getNombre()+" "+odontologo.getApellido()+ ", matricula:"+odontologo.getMatricula());
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        }
        else {
            logger.error("Error: No se encontro el odontologo con id: " + odontologo.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            logger.info("Se elimino al odontologo con id:"+ id);
            return ResponseEntity.ok("Se eliminó al odontologo con id= " +id);
        }
        else{
            logger.error("Error: No se encontro el odontologo con id: " + id);
            throw new ResourceNotFoundException("Error: No se pudo eliminar al odontologo con id= " + id + " porque no existe.");
        }
    }
}

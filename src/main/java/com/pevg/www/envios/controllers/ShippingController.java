package com.pevg.www.envios.controllers;

import com.pevg.www.envios.dtos.ShippingStatus;
import com.pevg.www.envios.dtos.TransitionRequest;
import com.pevg.www.envios.entities.Shipping;
import com.pevg.www.envios.exceptions.InvalidTransitionException;
import com.pevg.www.envios.services.ShippingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shippings")
@AllArgsConstructor
@Tag(name = "Shippings", description = "Operaciones relacionadas con envíos")
public class ShippingController {

    @Autowired
    private final ShippingService shippingService;
    
    @GetMapping("")
    @Operation(summary = "Obtener todos los envíos", description = "Retorna un arreglo con todos los envíos.")
    public ResponseEntity<List<Shipping>> getAll(){
        return shippingService.getAll();
    }
    
    @GetMapping("/status")
    @Operation(summary = "Obtener status de todos los envíos", description = "Retorna el status de cada uno de los envíos.")
    public ResponseEntity<List<ShippingStatus>> getStatus(){
        return shippingService.getStatus();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalles de un envío por ID", description = "Retorna los detalles de un envío específico.")
    private ResponseEntity<?> getById(@PathVariable Integer id){
        return shippingService.getById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Cambia el estado de un envío", description = "Actualiza el estado de un envío")
    private ResponseEntity<?> changeState(@PathVariable Integer id, @RequestBody TransitionRequest request){
        try{
            Shipping updateShipping = shippingService.changeState(id, request);
            return new ResponseEntity<>(updateShipping, HttpStatus.OK);
        } catch (InvalidTransitionException e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null,  HttpStatus.NOT_FOUND);
        }
    }
    
}

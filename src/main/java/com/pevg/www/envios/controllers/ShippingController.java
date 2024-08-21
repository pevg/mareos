package com.pevg.www.envios.controllers;

import com.pevg.www.envios.dtos.ShippingStatus;
import com.pevg.www.envios.dtos.TransitionRequest;
import com.pevg.www.envios.entities.Shipping;
import com.pevg.www.envios.exceptions.InvalidTransitionException;
import com.pevg.www.envios.services.ShippingService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shippings")
@AllArgsConstructor
public class ShippingController {

    @Autowired
    private final ShippingService shippingService;
    
    @GetMapping("")
    public ResponseEntity<List<Shipping>> getAll(){
        return shippingService.getAll();
    }
    
    @GetMapping("/status")
    public ResponseEntity<List<ShippingStatus>> getStatus(){
        return shippingService.getStatus();
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable Integer id){
        return shippingService.getById(id);
    }

    @PatchMapping("/{id}")
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

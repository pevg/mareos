package com.pevg.www.envios.controllers;

import com.pevg.www.envios.entities.Customer;
import com.pevg.www.envios.services.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Tag(name = "Customers", description = "Operaciones relacionadas con clientes")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    
    @GetMapping("")
    @Operation(summary = "Obtener todos los clientes", description = "Retorna un arreglo con todos los clientes.")
    public ResponseEntity<List<Customer>> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente por ID", description = "Retorna los detalles de un cliente específico.")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return customerService.getById(id);
    }

}

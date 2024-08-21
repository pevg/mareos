package com.pevg.www.envios.controllers;

import com.pevg.www.envios.entities.Customer;
import com.pevg.www.envios.services.CustomerService;

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
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    
    @GetMapping("")
    public ResponseEntity<List<Customer>> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return customerService.getById(id);
    }

}

package com.pevg.www.envios.services;

import com.pevg.www.envios.entities.Customer;
import com.pevg.www.envios.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    public ResponseEntity<Customer> getById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }
}

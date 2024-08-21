package com.pevg.www.envios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pevg.www.envios.entities.TaskShipping;
import com.pevg.www.envios.services.TaskShippingService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskShippingController {

    @Autowired
    private final TaskShippingService taskShippingService;

    @GetMapping("/{id}")
    public TaskShipping getByShippingId(@PathVariable Integer id) {
        return taskShippingService.getByShippingId(id);
    }

    @PostMapping()
    public TaskShipping create(@RequestBody Integer shipping_id) {
        System.out.println("entre por el post" + shipping_id);
        return taskShippingService.create(shipping_id);
    }    
    
    @PatchMapping("/{id}")
    public TaskShipping finish(@PathVariable Integer id) {
        return taskShippingService.finish(id);
    } 
    
}

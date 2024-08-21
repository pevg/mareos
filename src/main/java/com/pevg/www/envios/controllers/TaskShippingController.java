package com.pevg.www.envios.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "TaskShippings", description = "Operaciones asociadas a las tareas que se ejecutan sobre una compra")
public class TaskShippingController {

    @Autowired
    private final TaskShippingService taskShippingService;
    @Operation(summary = "Optiene una tarea de compra en función de su Id", description = "Optiene una tarea de compra en función de su Id")
    @GetMapping("/{id}")

    public TaskShipping getByShippingId(@PathVariable Integer id) {
        return taskShippingService.getByShippingId(id);
    }

    @PostMapping()
    @Operation(summary = "Crea una nueva tarea de compra", description = "Crea una nueva tarea asociada a la compra en cuestión")
    public TaskShipping create(@RequestBody Integer shipping_id) {
        return taskShippingService.create(shipping_id);
    }    
    
    @PatchMapping("/{id}")
    @Operation(summary = "Finaliza una tarea de compra", description = "Termina una tarea de compra determinada")
    public TaskShipping finish(@PathVariable Integer id) {
        return taskShippingService.finish(id);
    } 
    
}

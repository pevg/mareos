package com.pevg.www.envios.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pevg.www.envios.entities.Shipping;
import com.pevg.www.envios.entities.TaskShipping;
import com.pevg.www.envios.repositories.ShippingRepository;
import com.pevg.www.envios.repositories.TaskShippingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskShippingService {

    @Autowired
    private final TaskShippingRepository taskShippingRepository;
    @Autowired
    private final ShippingRepository shippingRepository;

    public TaskShipping getByShippingId(int shippingId) {
        return taskShippingRepository.findByShippingId(shippingId).isPresent() ? taskShippingRepository.findByShippingId(shippingId).get() : null;
    }

    public TaskShipping create(int shippingId) {
        System.out.println("entre por service create" + shippingId);
        Shipping shipping = shippingRepository.findById(shippingId).orElseThrow(() -> new RuntimeException("No se encontró envío con el id suministrado"));
        TaskShipping taskShipping = new TaskShipping();
        taskShipping.setShipping(shipping);
        taskShipping.setStart_date(new Date());
        return taskShippingRepository.save(taskShipping);
    }

    public TaskShipping finish(int taskId) {
        TaskShipping taskShipping = taskShippingRepository.findById(taskId).orElseThrow(() -> new RuntimeException("No se encontró tarea de envío con el id suministrado"));;
        taskShipping.setEnd_date(new Date());
        return taskShippingRepository.save(taskShipping);
    }


}

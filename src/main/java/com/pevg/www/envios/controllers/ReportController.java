package com.pevg.www.envios.controllers;

import com.pevg.www.envios.dtos.ProductSummary;
import com.pevg.www.envios.repositories.ShippingItemRepository;
import com.pevg.www.envios.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private final ShippingService shippingService;

    public ReportController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping("/top-sent")
    ResponseEntity<List<ProductSummary>> getTopProducts(){
        return new ResponseEntity<>(shippingService.getTopProducts(), HttpStatus.OK);
    }
}

package com.pevg.www.envios.controllers;

import com.pevg.www.envios.dtos.ProductSummary;
import com.pevg.www.envios.repositories.ShippingItemRepository;
import com.pevg.www.envios.services.ShippingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Reports", description = "Operaciones de reportería")
public class ReportController {

    @Autowired
    private final ShippingService shippingService;

    public ReportController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping("/top-sent")
    @Operation(summary = "Obtener top 3 productos comprados", description = "Retorna un listado de los productos mas comprados.")
    ResponseEntity<List<ProductSummary>> getTopProducts(){
        return new ResponseEntity<>(shippingService.getTopProducts(), HttpStatus.OK);
    }
}

package com.pevg.www.envios.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingStatus {
    private int id;
    private String state;
}

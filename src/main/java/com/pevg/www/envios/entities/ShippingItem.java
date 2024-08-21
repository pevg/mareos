package com.pevg.www.envios.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "shipping_item")
public class ShippingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "shipping_id", nullable = false )
    @JsonIgnore
    private Shipping shipping;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false )
    @JsonIgnore
    private Product product;
    private int product_count;
}

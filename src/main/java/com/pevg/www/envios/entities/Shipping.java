package com.pevg.www.envios.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String state;
    private Date send_date;
    private Date arrive_date;
    private int priority;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false )
    @JsonIgnore
    private Customer customer;
    @OneToMany(mappedBy = "shipping", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShippingItem> shipping_items;
    @OneToMany(mappedBy = "shipping", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskShipping> task_shippings;
}

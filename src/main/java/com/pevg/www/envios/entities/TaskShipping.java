package com.pevg.www.envios.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "task_shipping")
public class TaskShipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "shipping_id", nullable = false )
    @JsonIgnore
    private Shipping shipping;
    private Date start_date;
    private Date end_date;
}

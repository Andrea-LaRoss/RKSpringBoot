package com.si.rkspringboot.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "car")
@Data
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(name = "manufacturer", nullable = false)
    private String brand;


    @Column(name = "model", nullable = false)
    private String model;


    @Column(name = "type", nullable = false)
    private String type;


    @Column(name = "num_plate", nullable = false, length = 128, unique = true)
    private String numPlate;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "reg_date")
    private LocalDate regDate;

    /*@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservation;*/

}

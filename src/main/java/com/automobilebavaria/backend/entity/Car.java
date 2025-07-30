package com.automobilebavaria.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernateSequence")
    @SequenceGenerator(name = "hibernateSequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "maker", nullable = false)
    private String maker;

    @Size(max = 255)
    @NotNull
    @Column(name = "model", nullable = false)
    private String model;

    @NotNull
    @Column(name = "year_model", nullable = false)
    private Integer yearModel;

    @Size(max = 255)
    @NotNull
    @Column(name = "price", nullable = false)
    private String price;

}

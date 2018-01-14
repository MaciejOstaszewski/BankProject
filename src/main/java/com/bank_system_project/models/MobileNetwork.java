package com.bank_system_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Table(name = "mobile_network")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MobileNetwork {

    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;


    @Column(name = "name", nullable = false)
    private String name;

    public MobileNetwork(String name) {
        this.name = name;
    }
}

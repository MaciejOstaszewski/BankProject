/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
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
    private long id;


    @Column(name = "name", nullable = false)
    private String name;

    public MobileNetwork(String name) {
        this.name = name;
    }
}

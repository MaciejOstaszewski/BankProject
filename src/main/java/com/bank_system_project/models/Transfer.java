package com.bank_system_project.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transfer")
@Getter
@Setter
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "recipients_name_and_adress")
    private String recipientsNameAndAdress;

    @Column(name = "recipients_account")
    private String recipientsAccount;

    @Column(name = "amount")
    @Positive
    private BigDecimal amount;

    @Column(name = "title")
    private String title;

    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "execution_date")
    private Date executionDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private String status;


    @Column(name = "repeat")
    private boolean repeat;



}

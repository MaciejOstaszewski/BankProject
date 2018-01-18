package com.bank_system_project.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions_history")
@Getter
@Setter
@NoArgsConstructor
public class TransactionsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String recipient;

    private BigDecimal amount;

    @Column(name = "recipients_account")
    private String recipientsAccount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "realization_date")
    private Date realizationDate;


}

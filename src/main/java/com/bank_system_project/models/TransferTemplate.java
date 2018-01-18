package com.bank_system_project.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer_template")
@Getter
@Setter
@NoArgsConstructor
public class TransferTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "recipients_name_and_adress")
    private String recipientsNameAndAdress;

    @Column(name = "recipients_account")
    private String recipientsAccount;

    @Column(name = "amount")
    @Positive
    private BigDecimal amount;

    @Column(name = "title")
    private String title;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private long templateId;

}

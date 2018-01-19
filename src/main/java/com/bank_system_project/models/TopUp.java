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
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "top_up")
@Setter
@Getter
@NoArgsConstructor
public class TopUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Transient
    private String repeatPhoneNumber;


    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="mobile_network_id", nullable = false)
    private MobileNetwork mobileNetwork;

    @Column(name = "amount")
    @Positive
    private BigDecimal amount;


    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "execution_date")
    private Date executionDate;

    @AssertTrue(message = "Numery telefonu są różne")
    private boolean isNumbersEquals(){
        return phoneNumber == null || repeatPhoneNumber == null || phoneNumber.equals(repeatPhoneNumber);
    }

}


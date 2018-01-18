package com.bank_system_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@NoArgsConstructor
@Getter
@Setter
public class UserFormObject {

    @Size(min = 4, max = 36)
    private String password;

    @Size(min = 4, max = 36)
    private String passwordConfirm;

    @Size(min = 4, max = 36)
    private String firstname;

    @Size(min = 4, max = 36)
    private String surname;

    @Size(min = 4, max = 36)
    private String email;

    @Size(min = 4, max = 36)
    private String adress;

    @AssertTrue
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }
}

package com.bank_system_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4, max = 36)
    private String username;

    @Size(min = 4, max = 36)
    private String firstname;
    @Size(min = 2, max = 36)
    private String surname;
    @Size(min = 4, max = 36)
    private String emial;
    private BigDecimal means;
    @Column(name = "account_number")
    private String accountNumber;
    @Size(min = 4, max = 36)
    private String adress;



    private String password;
    @Transient
    private String passwordConfirm;
    private boolean enabled = false;

    @AssertTrue
    private boolean isPasswordsEquals(){
        return password == null || passwordConfirm == null || password.equals(passwordConfirm);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String username){
        this(username, false);
    }

    public User(String username, boolean enabled){
        this.username = username;
        this.enabled = enabled;
    }

}

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

    private String password;

    private boolean enabled = false;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;





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

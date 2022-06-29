package com.si.rkspringboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(name = "email", nullable = false, length = 128, unique = true)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "birthday")
    private LocalDate birthday;


    @Column(name = "is_admin", nullable = false)
    private boolean admin;


    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations;

    @Override
    public String toString() {
        return "Email:" + this.email + " Password: " + this.password;
    }

}

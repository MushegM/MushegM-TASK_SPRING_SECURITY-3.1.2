package ru.kata.spring.boot_security.demo.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String role;



        @Transient
    @ManyToMany
    private List<User> users;

    @Override
    public String getAuthority() {
        return getRole();
    }

    @Override
    public String toString() {
        return  role;
    }
}

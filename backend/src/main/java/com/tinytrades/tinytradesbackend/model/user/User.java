package com.tinytrades.tinytradesbackend.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinytrades.tinytradesbackend.model.product.Clothing;
import com.tinytrades.tinytradesbackend.model.product.Toy;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private final Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "trader", fetch = FetchType.LAZY)
    private final Set<Clothing> clothes = new HashSet<>();

    @OneToMany(mappedBy = "trader", fetch = FetchType.LAZY)
    private final Set<Toy> toys = new HashSet<>();

    public void addUserRole(UserRole userRole){
        userRoles.add(userRole);
    }


}

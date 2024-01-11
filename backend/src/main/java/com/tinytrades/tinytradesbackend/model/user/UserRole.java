package com.tinytrades.tinytradesbackend.model.user;

import com.tinytrades.tinytradesbackend.security.model.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> users;
}

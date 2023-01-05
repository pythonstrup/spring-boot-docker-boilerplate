package com.pythonstrup.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @NotNull
    @Size(min=4, max=20)
    @Column(nullable = false)
    private String password;
}

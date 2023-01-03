package com.pythonstrup.demo.entity;

import jakarta.persistence.*;
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

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}

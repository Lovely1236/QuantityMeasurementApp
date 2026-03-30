package com.example.quantity_measurement_app.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;     // ✅ store full name
    private String email;
    private String password;
    private String role;
}


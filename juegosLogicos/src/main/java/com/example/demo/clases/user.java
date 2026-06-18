package com.example.demo.clases;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")  // Especifica el nombre de la tabla en la base de datos
public class user {  // Renombrado a 'User' para seguir la convención de Java

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generación automática del ID
    private Long iduser;

    @Column(unique = true, nullable = false)  // El nombre de usuario es único y no puede ser nulo
    private String username;

    @Column(unique = true, nullable = false)  // El email es único y no puede ser nulo
    private String email;

    @Column(nullable = false)  // La contraseña no puede ser nula
    private String password;

    // Constructor por defecto (requerido por JPA)
    public user() {
    }

    // Constructor con parámetros (opcional, útil para crear instancias de la clase)
    public user(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

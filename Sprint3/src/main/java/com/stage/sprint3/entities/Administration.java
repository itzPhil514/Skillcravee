package com.stage.sprint3.entities;

import javax.persistence.*;

@Entity
@Table(name = "administration")
public class Administration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom", length = 120, unique = true, nullable = false)
    private String nom;

    @Column(name = "prenom", length = 120, unique = true, nullable = false)
    private String prenom;

    @Column(name="email", length = 128, nullable = false, unique = true)
    private String email;
    @Column(name = "password",length = 64, nullable = false)
    private String password;
    @Column(name="username", length = 128, nullable = false, unique = true)
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


package com.stage.sprint3.entities;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "professeur")
public class Prof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProf;
    @Column(name = "nom", length = 120, nullable = false)
    private String nom;
    @Column(name = "prenom", length = 120, nullable = false)
    private String prenom;
    @Column(name="email", length = 128, nullable = false, unique = true)
    private String email;
    @Column(name = "password",length = 64, nullable = false)
    private String password;

    public Prof() { }
    public Prof( String nom, String prenom,String email, String password)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    public Prof(int idProf, String nom, String prenom, String email, String password) {
        this.idProf = idProf;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }
    public int getIdProf(){
        return idProf;
    }
    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }
    public String getPassword() {return password;}
    public void setPassword(String password) {
        this.password = password;
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
}
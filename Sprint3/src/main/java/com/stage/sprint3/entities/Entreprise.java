package com.stage.sprint3.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInc;
    @Column(name = "nom", length = 120, unique = true, nullable = false)
    private String nom;
    @Column(name = "address", length = 120, nullable = false)
    private String address;
    @Column(name="telephone", length = 128, nullable = false)
    private String telephone;
    @Column(name="email", length = 128, nullable = false, unique = true)
    private String email;
    @Column(name = "password",length = 64, nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entreprise")
    private List<Emploi> emplois;

    public Entreprise() {
    }
    public Entreprise( String nom, String address,String email, String password) {
        this.nom = nom;
        this.address = address;
        this.email = email;
        this.password = password;
    }
    public Entreprise(String nom, String address, String telephone, String email, String password) {
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        emplois = new ArrayList<>();
    }
    public Entreprise(int idInc, String nom, String address, String telephone, String email, String password) {
        this.idInc = idInc;
        this.email = email;
        this.nom = nom;
        this.address = address;
        this.password = password;
        this.telephone = telephone;
        emplois = new ArrayList<>();
    }

    public Integer getIdInc() {
        return idInc;
    }

    public void setIdInc(Integer idInc) {
        this.idInc = idInc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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


    public List<Emploi> getEmplois() {
        return emplois;
    }

    public void setEmplois(List<Emploi> emplois) {
        this.emplois = emplois;
    }

    public Emploi getEmploi(Integer emploiId) {
        for (Emploi emploi : emplois) {
            if (emploi.getId() == emploiId) {
                return emploi;
            }
        }
        return null;
    }

}
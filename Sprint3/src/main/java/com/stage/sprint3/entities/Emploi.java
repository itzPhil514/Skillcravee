package com.stage.sprint3.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "emploi")
public class Emploi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titre", length = 120, unique = true, nullable = false)
    private String titre;

    @Column(name = "description", length = 120, nullable = false)
    private String description;

    @Column(name = "Date_debut")
    private String Date_debut;

    @Column(name = "Date_fin")
    private String Date_fin;

    @Column(name = "salaire", length = 64)
    private String salaire;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_inc", nullable = false)
    private Entreprise entreprise;

    @Transient
    private String entrepriseNom;
    public Emploi() {

    }

    public Emploi(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Emploi(int id, String titre, String description, String date_debut, String date_fin, String salaire, Entreprise entreprise) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        Date_debut = date_debut;
        Date_fin = date_fin;
        this.salaire = salaire;
        this.entreprise = entreprise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(String date_debut) {
        Date_debut = date_debut;
    }

    public String getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(String date_fin) {
        Date_fin = date_fin;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }


    public Entreprise getEntreprise() {
        return entreprise;
    }
    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

}
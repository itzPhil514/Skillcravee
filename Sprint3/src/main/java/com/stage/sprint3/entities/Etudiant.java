package com.stage.sprint3.entities;

import javax.persistence.*;
@Entity
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom", length = 120, nullable = false)
    private String nom;
    @Column(name = "prenom", length = 120, nullable = false)
    private String prenom;
    @Column(name="email", length = 128, nullable = false, unique = true)
    private String email;
    @Column(name = "password",length = 64, nullable = false)
    private String password;
    private boolean statusStage;
    @Column(name="numeroStage", nullable = false)
    private int numeroStage;
    @Lob
    private byte[] data;

    @Column(length = 64)
    private String CV;
    private boolean retenir;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_emp", nullable = true)
    private Emploi emploi;


    @ManyToOne(optional = true)
    @JoinColumn(name = "idProf", nullable = true)
    private Prof prof;




    public Etudiant() {}
    public Etudiant( String nom, String prenom,String email, String password)
    { this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    public Etudiant(int id, String nom, String prenom, String email, String password, int numeroStage, String CV)
    {   this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.numeroStage = numeroStage;
        this.CV = CV;
    }
    public int getId()
    { return id; }
    public void setId(int id)
    { this.id = id; }
    public String getNom()
    { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isStatusStage() { return statusStage; }
    public void setStatusStage(boolean statusStage) { this.statusStage = statusStage; }
    public int getNumeroStage() { return numeroStage; }
    public void setNumeroStage(int numeroStage) { this.numeroStage = numeroStage; }
    public boolean isRetenir() { return retenir; }
    public void setRetenir(boolean retenir) { this.retenir = retenir; }

    public String getCV(){
        return CV;
    }
    public void setCV(String CV){
        this.CV = CV;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    public Emploi getEmploi() {
        return emploi;
    }
    public void setEmploi(Emploi emploi) {
        this.emploi = emploi;
    }


    public Prof getProf() {
        return prof;
    }
    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public int getProfId() {
        if (prof != null) {
            return prof.getIdProf();
        }
        return 0;
    }



}
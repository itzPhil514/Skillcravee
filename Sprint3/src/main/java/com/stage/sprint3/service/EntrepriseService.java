package com.stage.sprint3.service;

import com.stage.sprint3.entities.Emploi;
import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Etudiant;
import com.stage.sprint3.repos.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository incRepo;

    @Autowired
    private EmploiService emploiService;

    @Autowired
    private EtudiantService etudiantService;

    public List<Entreprise> afficherEntreprises(){
        return (List<Entreprise>) incRepo.findAll();
    }

    public Entreprise ajouterEntreprise(Entreprise entreprise) {
        return incRepo.save(entreprise);
    }

    public void supprimer(Integer id) {
        incRepo.deleteById(id);
    }

    public List<Emploi> getEmploisByEntreprise(Entreprise entreprise) {
        List<Emploi> emploisByEntreprise = new ArrayList<>();
        List<Emploi> allEmplois = emploiService.afficherEmplois();
        for (Emploi emploi : allEmplois) {
            if (emploi.getEntreprise().equals(entreprise)) {
                emploisByEntreprise.add(emploi);
            }
        }
        return emploisByEntreprise;
    }

    public List<Entreprise> rechercherEntrepriseParNom(String keyword){
        if(keyword != null){
            return incRepo.findAll(keyword);
        }
        return null;
    }

    public Entreprise findById(Integer id) {
        return incRepo.findById(id).orElse(null);
    }

    public Entreprise get(Integer id){
        return incRepo.findById(id).get();
    }

    public Entreprise loginEntreprise(String email, String password){
        if(email != null && password != null){
            return incRepo.findByEmailAndPassword(email, password);
        }
        return null;
    }

    public Entreprise getEntrepriseById(Integer id) {
        return incRepo.findById(id).orElse(null);
    }

    public boolean accepter(Integer etudiantId, Integer entrepriseId, Integer emploiId) {
        Etudiant etudiant = etudiantService.getEtudiantById(etudiantId);
        Entreprise entreprise = this.getEntrepriseById(entrepriseId);
        Emploi emploi = emploiService.getEmploiById(emploiId);

        if (etudiant != null && entreprise != null && emploi != null) {
            etudiant.setStatusStage(true);
            etudiant.setEmploi(emploi);
            etudiantService.updateEtudiant(etudiant);
            return true;
        }
        return false;
    }
}

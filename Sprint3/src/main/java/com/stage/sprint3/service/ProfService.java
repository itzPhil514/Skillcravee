package com.stage.sprint3.service;

import com.stage.sprint3.entities.Emploi;
import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Etudiant;
import com.stage.sprint3.entities.Prof;
import com.stage.sprint3.repos.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProfService {

    @Autowired
    private ProfRepository profRepo;
    @Autowired
    EtudiantService etudiantService;

    public List<Prof> afficherProfs(){
        return (List<Prof>) profRepo.findAll();
    }
    public Prof ajouterProf(Prof prof) { return profRepo.save(prof);}


    public void supprimer(Integer id) {
        profRepo.deleteById(id);
    }

    public List<Prof> rechercherProfParNom(String keyword){
        if(keyword != null){
            return profRepo.findAll(keyword);
        }
        return null;
    }
    public Prof findById(Integer id) {
        return profRepo.findById(id).orElse(null);
    }

    public Prof get(Integer id){return profRepo.findById(id).get();}

    public Prof loginProf(String email, String password){
        if(email != null && password != null){
            return profRepo.findByEmailAndPassword(email, password);
        }
        return null;
    }

    public List<Etudiant> getEtudiantByProf(Prof prof) {
        List<Etudiant> etudiantByProf = new ArrayList<>();
        List<Etudiant> allEtudiants = etudiantService.afficherEtudiants();
        for (Etudiant etudiant : allEtudiants) {
            if (etudiant.getProf().equals(prof)) {
                etudiantByProf.add(etudiant);
            }
        }
        return etudiantByProf;
    }

    public Prof getProfById(Integer id) {
        return profRepo.findById(id).orElse(null);
    }
}

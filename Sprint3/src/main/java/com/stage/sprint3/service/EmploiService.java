package com.stage.sprint3.service;

import com.stage.sprint3.entities.Emploi;
import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Prof;
import com.stage.sprint3.repos.EmploiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmploiService {
    @Autowired
    private EmploiRepository emploiRepo;
    public List<Emploi> afficherEmplois(){
        return (List<Emploi>) emploiRepo.findAll();
    }
    public Emploi ajouterEmploi(Emploi emploi) { return emploiRepo.save(emploi);}

    public Emploi findById(Integer id) {
        return emploiRepo.findById(id).orElse(null);
    }

    public List<Emploi> rechercherEmploiParTitre(String keyword){
        if(keyword != null){
            return emploiRepo.findAll(keyword);
        }
        return null;
    }

    public void supprimer(Integer id) {
        emploiRepo.deleteById(id);
    }

    public List<Emploi> afficherAscendant() {
        return emploiRepo.findByOrderByTitreAsc();
    }

    public List<Emploi> afficherDescendant() {
        return emploiRepo.findByOrderByTitreDesc();
    }

    public Emploi get(Integer id){return emploiRepo.findById(id).get();}

    public Emploi getEmploiById(Integer id) {
        return emploiRepo.findById(id).orElse(null);
    }
}

package com.stage.sprint3.service;

import com.stage.sprint3.entities.Administration;
import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.repos.AdministrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdministrateurService {

    @Autowired
    private AdministrationRepository adminRepo;

    public List<Administration> afficherAdmins(){
        return (List<Administration>) adminRepo.findAll();
    }
    public Administration ajouterAdmin(Administration administration) { return adminRepo.save(administration);}

    public void supprimer(Integer id) {
        adminRepo.deleteById(id);
    }



    public List<Administration> rechercherAdminParNom(String keyword){
        if(keyword != null){
            return adminRepo.findAll(keyword);
        }
        return null;
    }

/*
    public void updateActive(Integer id, boolean enabled){
        etudiantRepo.updateActiveStat(id, enabled);
    }

 */

    public Administration get(Integer id){return adminRepo.findById(id).get();}

    public Administration loginadmin(String email, String password){
        if(email != null && password != null){
            return adminRepo.findByEmailAndPassword(email, password);
        }
        return null;
    }
}

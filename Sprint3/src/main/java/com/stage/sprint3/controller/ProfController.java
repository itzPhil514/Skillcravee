package com.stage.sprint3.controller;

import com.stage.sprint3.entities.Emploi;
import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Etudiant;
import com.stage.sprint3.entities.Prof;
import com.stage.sprint3.service.EtudiantService;
import com.stage.sprint3.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProfController {

    @Autowired
    ProfService profService;
    @GetMapping("/profs")
    public String afficherProfs(Model model){
        Iterable<Prof> listeProfs = profService.afficherProfs();
        model.addAttribute("listeProfs",listeProfs);
        return "profs";
    }

    @GetMapping("/profs/delete/{id}")
    public String supprimerProf(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes){
        profService.supprimer(id);
        redirectAttributes.addFlashAttribute("message", "Le professeur ID " + id + " a été supprimé avec succès ");
        return "redirect:/profs";
    }
    @GetMapping("rechercher/profs")
    public String rechercherProf(Model model, @Param("keyword") String keyword){
        List<Prof> listeProfs = profService.rechercherProfParNom(keyword);
        model.addAttribute("listeProfs", listeProfs);
        model.addAttribute("keyword", keyword);
        return "profs";
    }

    @PostMapping("/profs/save")
    public String editerProf(Prof prof, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","Le professeurs a été édité avec success");
        profService.ajouterProf(prof);
        return "redirect:/profs";
    }
    @GetMapping("/profs/edit/{id}")
    public String EditerProf(@PathVariable(name = "id") Integer id, Model model){
        Prof prof = profService.get(id);
        model.addAttribute("PageTitle", "Editer Professeur ID: " + id);
        model.addAttribute("prof", prof);
        return "prof-editer";
    }


    @GetMapping("/etudiants_prof/{id}")
    public String afficherEtudiantParProf(@PathVariable("id") Integer id, Model model){
        Prof prof = profService.findById(id);
        List<Etudiant> listeEtudiantProf= profService.getEtudiantByProf(prof);
        model.addAttribute("prof", prof);
        model.addAttribute("listeEtudiantProf", listeEtudiantProf);
        model.addAttribute("nomProf", prof.getNom());
        return "etudiants_prof";
    }

}

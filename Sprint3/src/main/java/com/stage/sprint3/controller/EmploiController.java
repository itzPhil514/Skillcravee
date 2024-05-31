package com.stage.sprint3.controller;

import com.stage.sprint3.entities.Emploi;
import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Prof;
import com.stage.sprint3.service.EmploiService;
import com.stage.sprint3.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmploiController {
    @Autowired
    EmploiService emploiService;
    @Autowired
    EntrepriseService incService;
    @GetMapping("")
    public String afficherEmplois(Model model) {
        Iterable<Emploi> listeEmplois = emploiService.afficherEmplois();
        model.addAttribute("listeEmplois", listeEmplois);
        return "index";
    }

    @GetMapping("rechercher/index")
    public String rechercherEmploi(Model model, @Param("keyword") String keyword){
        List<Emploi> listeEmplois = emploiService.rechercherEmploiParTitre(keyword);
        model.addAttribute("listeEmplois", listeEmplois);
        model.addAttribute("keyword", keyword);
        return "index";
    }


    @GetMapping("/emplois_entreprise/delete/{id}")
    public String supprimerEntreprise(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes){
        emploiService.supprimer(id);
        redirectAttributes.addFlashAttribute("message", "L'emploi ID " + id + " de l'entrepise a été supprimé avec succès ");
        return "redirect:/entreprises";
    }

    @GetMapping("/filtrer/index")
    public String filtrerEmplois(Model model, @Param("sort") String sort) {
        List<Emploi> listeEmplois;
        if ("asc".equals(sort)) {
            listeEmplois = emploiService.afficherAscendant();
        } else if ("desc".equals(sort)) {
            listeEmplois = emploiService.afficherDescendant();
        } else {
            listeEmplois = emploiService.afficherEmplois();
        }
        model.addAttribute("listeEmplois", listeEmplois);
        return "index";
    }

    @GetMapping("/emploi/new")
    public String afficherFormulaireEmploi(Model model){
        Emploi emploi = new Emploi();
        List<Entreprise> listeEntreprise = incService.afficherEntreprises();
        model.addAttribute("emploi", emploi);
        model.addAttribute("listeEntreprise", listeEntreprise);
        model.addAttribute("pageTitle", "Ajouter un nouveau emploi");

        return "emploi-editer";
    }

    @PostMapping("/emploi/save")
    public String ajouterEmploi(Emploi emploi, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","L'emploi a été ajouté avec success");
        emploiService.ajouterEmploi(emploi);
        return "index";
    }
    @GetMapping("/emploi/edit/{id}")
    public String EditerEmploi(@PathVariable(name = "id") Integer id, Model model){
        Emploi emploi = emploiService.get(id);
        List<Entreprise> listeEntreprise = incService.afficherEntreprises();
        model.addAttribute("PageTitle", "Editer Emploi ID: " + id);
        model.addAttribute("emploi",emploi);
        model.addAttribute("listeEntreprise",listeEntreprise);
        return "emploi-editer";
    }

}

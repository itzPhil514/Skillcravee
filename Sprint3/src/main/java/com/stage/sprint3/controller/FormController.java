package com.stage.sprint3.controller;

import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Etudiant;
import com.stage.sprint3.entities.Formulaire;
import com.stage.sprint3.entities.Prof;
import com.stage.sprint3.service.EntrepriseService;
import com.stage.sprint3.service.EtudiantService;
import com.stage.sprint3.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
public class FormController {
    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private ProfService profService;

    @GetMapping("index/new")
    public String showForm(Model model) {
        model.addAttribute("formulaire", new Formulaire());
        List<Prof> listeProf = profService.afficherProfs();
        model.addAttribute("listeProf", listeProf);
        return "inscription_form";
    }

    @RequestMapping(value = "/index/save", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("formulaire") Formulaire formulaire, @RequestParam("selection") String selection, RedirectAttributes redirectAttributes,@RequestParam("file") MultipartFile multipartFile,Model model) throws IOException {
        if (selection.equals("Étudiant")) {

            Etudiant etudiant = new Etudiant();
            etudiant.setNom(formulaire.getEtudiant().getNom());
            etudiant.setPrenom(formulaire.getEtudiant().getPrenom());
            etudiant.setEmail(formulaire.getEtudiant().getEmail());
            etudiant.setPassword(formulaire.getEtudiant().getPassword());
            etudiant.setEmploi(formulaire.getEtudiant().getEmploi());
            etudiant.setProf(formulaire.getEtudiant().getProf());

            long maxSize = 30000000;
            long fileSize = multipartFile.getSize();
            if (fileSize > maxSize) {
                model.addAttribute("message","la taille " + fileSize + " du fichier dépasse la taille limite autorisée qui est " + maxSize + " soit 10MB ");
                return "inscription_form";
            }

            String chemin = multipartFile.getOriginalFilename();
            String typeContenu = multipartFile.getContentType();

            String fileName = StringUtils.cleanPath(chemin);

            etudiant.setCV(fileName);
            etudiant.setData(multipartFile.getBytes());

            File directory = new File("src/main/resources/upload");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File serverFile = new File(directory.getAbsolutePath() + File.separator + fileName);
            multipartFile.transferTo(serverFile);

            etudiantService.ajouterEtudiant(etudiant);
            redirectAttributes.addFlashAttribute("message", "L'étudiant est bien ajouté");

        } else if (selection.equals("Entreprise")) {
            Entreprise entreprise = new Entreprise();
            entreprise.setNom(formulaire.getEntreprise().getNom());
            entreprise.setAddress(formulaire.getEntreprise().getAddress());
            entreprise.setTelephone(formulaire.getEntreprise().getTelephone());
            entreprise.setEmail(formulaire.getEntreprise().getEmail());
            entreprise.setPassword(formulaire.getEntreprise().getPassword());
            entrepriseService.ajouterEntreprise(entreprise);
            redirectAttributes.addFlashAttribute("message", "L'entreprise est bien ajouté");
        } else if (selection.equals("Professeur")) {
            Prof prof = new Prof();
            prof.setNom(formulaire.getProf().getNom());
            prof.setPrenom(formulaire.getProf().getPrenom());
            prof.setEmail(formulaire.getProf().getEmail());
            prof.setPassword(formulaire.getProf().getPassword());
            profService.ajouterProf(prof);
            redirectAttributes.addFlashAttribute("message", "Le Prof est bien ajouté");
        }
        return "inscription_form";
    }
}
package com.stage.sprint3.controller;

import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Etudiant;
import com.stage.sprint3.entities.Formulaire;
import com.stage.sprint3.entities.Prof;
import org.hibernate.mapping.Formula;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class AccueilController {

    @GetMapping("/")
    public String pageAccueil (){
        return "index";
    }
}

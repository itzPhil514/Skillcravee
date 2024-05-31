package com.stage.sprint3.rest;

import com.stage.sprint3.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtudiantRestController {
    @Autowired
    EtudiantService etudiantService;
    @PostMapping("/index/check_email")
    public String verifierDoublonEmail(@Param("etudiant.email") String email, @Param("etudiant.email") Integer id) {
        return etudiantService.isEmailUnique(email,id) ? "OK" : "Doublon";
    }
    /*
    @PostMapping("/etudiant_editer/check_email")
    public String verifierDoublonEmailEditer(@Param("email") String email, @Param("id") Integer id) {
        return etudiantService.isEmailUnique(email,id) ? "OK" : "Doublon";
    }
    @PostMapping("/etudiant_editer2/check_email")
    public String verifierDoublonEditer2(@Param("email") String email, @Param("id") Integer id) {
        return etudiantService.isEmailUnique(email,id) ? "OK" : "Doublon";
    }

     */
}

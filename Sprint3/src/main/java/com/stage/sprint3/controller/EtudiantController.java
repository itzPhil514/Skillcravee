package com.stage.sprint3.controller;

import com.stage.sprint3.entities.Entreprise;
import com.stage.sprint3.entities.Etudiant;
import com.stage.sprint3.entities.Prof;
import com.stage.sprint3.repos.EtudiantRepository;
import com.stage.sprint3.service.EtudiantService;
import com.stage.sprint3.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class EtudiantController {

    @Autowired
    EtudiantService etudiantService;
    @Autowired
    ProfService profService;

    @Autowired
    EtudiantRepository etudiantRepository;
    @GetMapping("/etudiants")
    public String afficherEtudiants(Model model){
        Iterable<Etudiant> listeEtudiants = etudiantService.afficherEtudiants();
        model.addAttribute("listeEtudiants",listeEtudiants);
        return "etudiants";
    }

    @GetMapping("rechercher/etudiants")
    public String rechercherEtudiant(Model model, @Param("keyword") String keyword){
        List<Etudiant> listeEtudiants = etudiantService.rechercherEtudiantParNom(keyword);
        model.addAttribute("listeEtudiants", listeEtudiants);
        model.addAttribute("keyword", keyword);
        return "etudiants";
    }

    @GetMapping("/etudiants/delete/{id}")
    public String supprimerEtudiant(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes){
        etudiantService.supprimer(id);
        redirectAttributes.addFlashAttribute("message", "L'etudiant ID " + id + " a été supprimé avec succès ");
        return "redirect:/etudiants";
    }

    @GetMapping("/etudiants/{id}/stage/{status}")
    public String changerStage(@PathVariable("id") Integer id, @PathVariable("status") boolean statusStage, RedirectAttributes redirectAttributes) {
        etudiantService.updateStatus(id, statusStage);
        String status = statusStage ? "active" : "desactive";
        String message = "L'étudiant " + id + " travaille dans un stage: " + status;
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/etudiants";
    }

    @PostMapping("/etudiants/save")
    public String editerEtud(Etudiant etud, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile multipartFile, Model model) throws IOException {

        long maxSize = 30000000;
        long fileSize = multipartFile.getSize();
        if (fileSize > maxSize) {
            model.addAttribute("message","la taille " + fileSize + " du fichier dépasse la taille limite autorisée qui est " + maxSize + " soit 10MB ");
            return "inscription_form";
        }

        String chemin = multipartFile.getOriginalFilename();
        String typeContenu = multipartFile.getContentType();

        String fileName = StringUtils.cleanPath(chemin);

        etud.setCV(fileName);
        etud.setData(multipartFile.getBytes());

        File directory = new File("src/main/resources/upload");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File serverFile = new File(directory.getAbsolutePath() + File.separator + fileName);
        multipartFile.transferTo(serverFile);

        redirectAttributes.addFlashAttribute("message","L'étudiant a été édité avec success");
        etudiantService.ajouterEtudiant(etud);
        return "redirect:/etudiants";
    }
    @GetMapping("/etudiants/edit/{id}")
    public String EditerEtudiant(@PathVariable(name = "id") Integer id, Model model){
        Etudiant etud = etudiantService.get(id);
        List<Prof> listeProf = profService.afficherProfs();
        model.addAttribute("PageTitle", "Editer Étudiant ID: " + id);
        model.addAttribute("etud", etud);
        model.addAttribute("listeProf",listeProf);
        return "etudiant-editer2";
    }

    @GetMapping("/Admin_etudiants/etu_edit/{id}")
    public String EditerEtudiant2(@PathVariable(name = "id") Integer id, Model model){
        Etudiant etud = etudiantService.get(id);
        List<Prof> listeProf = profService.afficherProfs();
        model.addAttribute("PageTitle", "Editer Étudiant ID: ");
        model.addAttribute("etud", etud);
        model.addAttribute("listeProf",listeProf);
        return "etudiant-editer";
    }

    @PostMapping("/Admin_etudiants/save")
    public String editerEtud2(Etudiant etud, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile multipartFile, Model model) throws IOException {

        long maxSize = 10000000;
        long fileSize = multipartFile.getSize();
        if (fileSize > maxSize) {
            model.addAttribute("message","la taille " + fileSize + " du fichier dépasse la taille limite autorisée qui est " + maxSize + " soit 10MB ");
            return "inscription_form";
        }

        String chemin = multipartFile.getOriginalFilename();
        String typeContenu = multipartFile.getContentType();

        String fileName = StringUtils.cleanPath(chemin);

        etud.setCV(fileName);
        etud.setData(multipartFile.getBytes());

        File directory = new File("src/main/resources/upload");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File serverFile = new File(directory.getAbsolutePath() + File.separator + fileName);
        multipartFile.transferTo(serverFile);

        redirectAttributes.addFlashAttribute("message","Vous avez édité vos données avec success");
        etudiantService.ajouterEtudiant(etud);
        return "redirect:/";
    }

    @GetMapping("/etudiants/{id}/cv")
    public ResponseEntity<ByteArrayResource> getCV(@PathVariable Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));

        if (etudiant.getCV() == null) {
            throw new IllegalArgumentException("CV not found for student with ID: " + id);
        }

        // Convert CV string to byte array
        byte[] cvBytes = etudiant.getCV().getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "");

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(cvBytes));
    }

    @GetMapping("/etudiants_prof/delete/{id}")
    public String supprimerEntreprise(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes){
        etudiantService.supprimer(id);
        redirectAttributes.addFlashAttribute("message", "Votre étudiant ID " + id + " a été supprimé avec succès ");
        return "redirect:/";
    }

}

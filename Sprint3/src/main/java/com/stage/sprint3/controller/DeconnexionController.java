package com.stage.sprint3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DeconnexionController {

    @GetMapping("/deconnexion")
    public String processRequest(Model model, HttpSession session) {
        if (session != null) {
            session.invalidate();
            model.addAttribute("message", "Deconnexion réussi");
        }
        return "redirect:/";
    }
}

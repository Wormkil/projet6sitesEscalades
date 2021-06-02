package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class sitesEscaladesController {

    @GetMapping("/sitesEscalades")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        if (session.getAttribute("user") != null) return "sitesEscalades";
        else {
            session.setAttribute("user", null);
            return "sitesEscalades";
        }
    }
}

package fr.oc.amisdelescalade.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

public class sitesEscaladesController {

    @GetMapping("/SitesEscalades")
    public String index(Model model, HttpServletRequest request) {

        return "sitesEscalades";
    }
}

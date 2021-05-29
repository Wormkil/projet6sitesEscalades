package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomepageController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        //model.addAttribute("user", new Utilisateur(0,"aze@aze.com","pasdepasse","invite","invite"));
        /* Création ou récupération de la session */
      /*  HttpSession session = request.getSession();

        String user = Projet6Application.getUser();
        log.info("user = "+user);
        model.addAttribute("user", user);
        log.info("user 2 = "+model.getAttribute("user"));*/

        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        return "index";
    }
}

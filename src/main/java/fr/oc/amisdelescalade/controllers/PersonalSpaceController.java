package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Topo;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.SessionService;
import fr.oc.amisdelescalade.service.TopoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PersonalSpaceController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private SessionService sesService;
    @Autowired
    private TopoService topoService;

    @GetMapping("/mon-compte")
    public String get(HttpServletRequest request, Model model) {
        HttpSession session = sesService.OpenOrGetSession(request);

        //Est-ce il a le droit de faire la requete (par exemple il est bien connecté)

        //Est-ce il a le droit de faire sa (la requete) ?

        // Si oui j'execute le service

        //get info user
        User u = sesService.getUserFromSession(session);
        model.addAttribute("user", u);
        //affiché info

        //get info User topos
        Iterable<Topo> topos = topoService.getToposByAuthorId(Long.toString(u.getId()));
        log.info(topos.toString());
        model.addAttribute("topos", topos);

        //affiché User topos

        return "personalSpace";
    }
}

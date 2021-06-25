package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Topo;
import fr.oc.amisdelescalade.model.Topos;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.SessionService;
import fr.oc.amisdelescalade.service.TopoService;
import fr.oc.amisdelescalade.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PersonalSpaceController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private SessionService sesService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopoService topoService;

    @GetMapping("/mon-compte")
    public String get(HttpServletRequest request, Model model) {
        //******************************** Récupération des informations du visiteur ************
        //Connect automatiquement un utilisateur officiel quand on arrive sur cette page
        User u = userService.getUserById(1L).get();
        model.addAttribute("user", u);
        HttpSession session = sesService.OpenOrGetSession(request);
        session.setAttribute("user", u);
        //User u = sesService.getUserFromSession(session);<-- Remplace la connection automatique ci-dessus

        //*************************** Verification Droits d'accès Utilisateur *******************
        //On vérifie qu'il y ait bien un utilisateur connecté sinon on redirige vers une page d'erreur
        if(u == null) return sesService.redirectToErrorPage(request);

        //On vérifie que l'utilisateur est bien un 'official' sinon on redirige vers une page d'érreur
        if (!sesService.checkUserCanDoThisRequest(request, "official"))
            return sesService.redirectToErrorPage(request);

        //******************************** Débbuging/test ***************************************
        Topo unTopo = topoService.getTopoById(1L).get();
        model.addAttribute("unTopo", unTopo);

        //******************************** Service normal ***************************************
        //Fonctionnement normal : récupère tous les topos de l'utilisateur et les envoi au model attribute
        Iterable<Topo> tmptopos = topoService.getToposByAuthorId(Long.toString(u.getId()));
        model.addAttribute("topos", tmptopos);
        return "personalSpace";
    }

    @PostMapping("/mon-compte")
    public String post(HttpServletRequest request, Model model,
                        @ModelAttribute("topo")Topo topo,
                        @ModelAttribute("topos")Topos topos) {
        //******************************** Récupération des informations du visiteur ************
        //Connect automatiquement un utilisateur officiel quand on arrive sur cette page
        User u = userService.getUserById(1L).get();
        model.addAttribute("user", u);
        HttpSession session = sesService.OpenOrGetSession(request);
        session.setAttribute("user", u);
        //User u = sesService.getUserFromSession(session);<-- Remplace la connection automatique ci-dessus

        //******************************** Verification *****************************************
        //On vérifie qu'il y ait bien un utilisateur connecté sinon on redirige vers une page d'erreur
        if(u == null) return sesService.redirectToErrorPage(request);

        //On vérifie que l'utilisateur est bien un 'official' sinon on redirige vers une page d'érreur
        if (!sesService.checkUserCanDoThisRequest(request, "official"))
            return sesService.redirectToErrorPage(request);

        //******************************** Débbuging/test ***************************************
        Topo unTopo = topoService.getTopoById(1L).get();
        model.addAttribute("unTopo", unTopo);

        //******************************** Service normal ***************************************
        //Sauvegarde en bado les modifications des topos
        log.info("topo : "+topo.toString());
        log.info("topos : "+topos.toString());

        return "personalSpace";
    }
}

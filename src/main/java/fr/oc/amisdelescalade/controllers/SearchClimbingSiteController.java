package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchClimbingSiteController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final String currentUrl = "recherche-site-escalade";

    @Autowired
    private SessionService sesService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopoService topoService;
    @Autowired
    private UtilitairesService utils;
    @Autowired
    private ClimbSitesService climbSitesService;

    @GetMapping("/recherche-site-escalade")
    public String get(HttpServletRequest request) {
        sesService.getRequestStarter(request, currentUrl);

        return currentUrl;
    }

    @RequestMapping("/recherche-site-escalade")
    public String post(HttpServletRequest request,Model model,
                       @ModelAttribute ClimbSites csSearched) {

        Iterable<ClimbSites> allCss = climbSitesService.getCSs();
        List<ClimbSites> searchedCss = new ArrayList<>();

        for (ClimbSites cs:allCss) if (climbSitesService.compareCs(cs, csSearched)) searchedCss.add(cs);
        log.info("searchedCss = "+searchedCss);
        model.addAttribute("css", searchedCss);

        return "resultat-recherche";
    }
}

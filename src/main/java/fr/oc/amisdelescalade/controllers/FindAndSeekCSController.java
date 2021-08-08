package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.service.ClimbSitesService;
import fr.oc.amisdelescalade.service.SessionService;
import fr.oc.amisdelescalade.service.UtilitairesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class FindAndSeekCSController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final String currentUrl = "trouver-un-site-descalade";
    private final int maxElementByPage = 5;

    @Autowired
    private ClimbSitesService csService;
    @Autowired
    private SessionService sesService;
    @Autowired
    private UtilitairesService utils;
    @Autowired
    private ClimbSitesService climbSitesService;

    @GetMapping("/trouver-un-site-descalade{page}")
    public String get(Model model, HttpServletRequest request, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        sesService.getRequestStarter(request, currentUrl);

        Iterable<ClimbSites> csForView = csService.getCSs();
        var numberCs = ((Collection<?>) csForView).size();

        if (sesService.pageAskedIsCorrect(page, numberCs / maxElementByPage)) return sesService.redirectToErrorPage(request);

        model.addAttribute("listPage", utils.getListPage(page, numberCs, maxElementByPage));
        model.addAttribute("css", utils.truncateIterableByParameters(page, maxElementByPage, csForView));
        model.addAttribute("currentPage", page);

        return currentUrl;
    }
    @GetMapping("/recherche-site-escalade")
    public String get(HttpServletRequest request) {
        sesService.getRequestStarter(request, currentUrl);

        return "recherche-site-escalade";
    }
    @RequestMapping("/recherche-site-escalade")
    public String post(HttpServletRequest request,Model model,
                       @ModelAttribute ClimbSites csSearched) {

        Iterable<ClimbSites> allCss = climbSitesService.getCSs();
        List<ClimbSites> searchedCss = new ArrayList<>();

        for (ClimbSites cs:allCss) if (climbSitesService.compareCs(cs, csSearched)) searchedCss.add(cs);

        model.addAttribute("css", searchedCss);

        return "resultat-recherche";
    }
}

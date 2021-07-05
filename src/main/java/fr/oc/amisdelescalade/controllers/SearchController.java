package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.ClimbSitesService;
import fr.oc.amisdelescalade.service.SessionService;
import fr.oc.amisdelescalade.service.UtilitairesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final int maxElementByPage = 5;

    @Autowired
    private ClimbSitesService csService;
    @Autowired
    private SessionService sesService;
    @Autowired
    private UtilitairesService utils;

    @GetMapping("/trouver-un-site-descalade{page}")
    public String get(Model model, HttpServletRequest request,
                      @RequestParam(value = "page", required = false, defaultValue = "1") int page
    ) {
        HttpSession session = sesService.OpenOrGetSession(request);
        User u = sesService.getUserFromSession(session);


        //******************************** Service normal ***************************************
        Iterable<ClimbSites> csForView = csService.getCSs();
        var numberCs = ((Collection<?>) csForView).size();
        if ((page > (numberCs / maxElementByPage ) + 1 && page != 1) || page <= 0) {
            return sesService.redirectToErrorPage(request);
        }
        Map<String, Integer> parameters = Map.of("currentPage", page, "elementNumber", numberCs, "maxElementByPage", maxElementByPage);
        List<Integer> listPage = utils.getListPage(parameters);

        var nbTopoToTruncate = (page-1) * maxElementByPage;
        if (page > 1) csForView = ((List<ClimbSites>) csForView).stream().skip(nbTopoToTruncate).limit(maxElementByPage).toList();
        else csForView = ((List<ClimbSites>) csForView).stream().limit(maxElementByPage).toList();

        model.addAttribute("listPage", listPage);
        model.addAttribute("css", csForView);
        model.addAttribute("currentPage", page);

        return "trouver-un-site-descalade";
    }
}

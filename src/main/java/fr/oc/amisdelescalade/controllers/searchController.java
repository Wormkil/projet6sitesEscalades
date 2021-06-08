package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.service.ClimbSitesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class searchController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private ClimbSitesService csService;

    @GetMapping("/trouver-un-site-descalade")
    public String get(Model model, HttpServletRequest request) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        session.setAttribute("user", null);

        Iterable<ClimbSites> css = csService.getCSs();
        model.addAttribute("css", css);
        ClimbSites acs = css.iterator().next();
        model.addAttribute("acs", acs);

        return "trouver-un-site-descalade";
    }

    @PostMapping("/trouver-un-site-descalade")
    public String post(Model model, HttpServletRequest request) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        session.setAttribute("user", null);

        String csId = request.getParameter("cs_id");
        log.info("csId : "+csId);

        return "trouver-un-site-descalade";
    }
}

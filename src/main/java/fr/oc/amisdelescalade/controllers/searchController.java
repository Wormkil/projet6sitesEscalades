package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.service.ClimbSitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class searchController {

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
}

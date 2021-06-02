package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Blocs;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.service.ClimbSitesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class sitesEscaladesController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    @Autowired
    private ClimbSitesService csService;

    @GetMapping("/sitesEscalades")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        session.setAttribute("user", null);

        ClimbSites cs = csService.getCSById(1L).get();
        Blocs bls = csService.getBlocsById(cs.getBlocs_id()).get();
        List<ClimbSites> lBl = csService.findAllBloc(cs.getBlocs_id());

        model.addAttribute("nameCS", cs.getName());
        model.addAttribute("sectorCS", cs.getSector());
        model.addAttribute("descriptifCS", cs.getDescription());
        model.addAttribute("difficultyCS", cs.getDifficulty());
        model.addAttribute("accessCS", cs.getAccess());

        return "sitesEscalades";
    }
}

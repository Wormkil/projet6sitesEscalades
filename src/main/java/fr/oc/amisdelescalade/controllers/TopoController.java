package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Topo;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.SessionService;
import fr.oc.amisdelescalade.service.TopoService;
import fr.oc.amisdelescalade.service.UserService;
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
public class TopoController {
    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final int maxElementByPage = 10;

    @Autowired
    private SessionService sesService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopoService topoService;
    @Autowired
    private UtilitairesService utilitairesService;


    @GetMapping("/Topos{page}")
    public String get(HttpServletRequest request, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {


        //******************************** Récupération des informations du visiteur ************


        //*************************** Verification Droits d'accès Utilisateur *******************




        //******************************** Service normal ***************************************
        HttpSession session = sesService.OpenOrGetSession(request);
        User u = sesService.getUserFromSession(session);
        model.addAttribute("guestIsConnect", "false");
        if (u != null) {
            model.addAttribute("guestIsConnect", "true");
        }

        Iterable<Topo> allTopo = topoService.getTopoRepository().findByAvailable("true");
        var numberTopo = ((Collection<?>) allTopo).size();

        Map<String, Integer> parameters = Map.of("currentPage", page, "elementNumber", numberTopo, "maxElementByPage", maxElementByPage);
        List<Integer> listPage = utilitairesService.getListPage(parameters);

        var nbTopoToTruncate = (page-1) * maxElementByPage;
        if (page > 1) allTopo = ((List<Topo>) allTopo).stream().skip(nbTopoToTruncate).limit(maxElementByPage).toList();
        else allTopo = ((List<Topo>) allTopo).stream().limit(maxElementByPage).toList();

        model.addAttribute("allTopo", allTopo);
        model.addAttribute("listPage", listPage);
        model.addAttribute("currentPage", page);

        return "topos";
    }
}

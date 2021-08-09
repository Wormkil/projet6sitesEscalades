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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class PersonalSpaceController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final int maxElementByPage = 5;
    private final String currentUrl = "personalSpace";

    @Autowired
    private SessionService sesService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopoService topoService;
    @Autowired
    private UtilitairesService utils;

    @GetMapping("/mon-compte{page}")
    public String get(HttpServletRequest request, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {


        //******************************** Récupération des informations du visiteur ************
        //Connect automatiquement un utilisateur officiel quand on arrive sur cette page
        User u = userService.getUserById(1L).get();
        model.addAttribute("user", u);
        HttpSession session = sesService.OpenOrGetSession(request);
        session.setAttribute("user", u);
        //User u = sesService.getUserFromSession(session);<-- Remplace la connection automatique ci-dessus

        //*************************** Verification Droits d'accès Utilisateur *******************
        //On vérifie qu'il y ait bien un utilisateur connecté sinon on redirige vers une page d'erreur
        if(u == null)
            return sesService.redirectToErrorPage(request);


        //On vérifie que l'utilisateur est bien un 'official' sinon on redirige vers une page d'érreur
        if (!sesService.checkUserCanDoThisRequest(request, "connect"))
            return sesService.redirectToErrorPage(request);


        //******************************** Service normal ***************************************
        Iterable<Topo> toposForView = topoService.getToposByAuthorId(Long.toString(u.getId()));

        var numberTopo = ((Collection<?>) toposForView).size();
        if ((page > (numberTopo / maxElementByPage ) + 1 && page != 1) || page <= 0) {
            return sesService.redirectToErrorPage(request);
        }

        Map<String, Integer> parameters = Map.of("currentPage", page, "elementNumber", numberTopo, "maxElementByPage", maxElementByPage);
        List<Integer> listPage = utils.getListPage(parameters);

        var nbTopoToTruncate = (page-1) * maxElementByPage;
        if (page > 1) toposForView = ((List<Topo>) toposForView).stream().skip(nbTopoToTruncate).limit(maxElementByPage).toList();
        else toposForView = ((List<Topo>) toposForView).stream().limit(maxElementByPage).toList();


        model.addAttribute("listPage", listPage);
        model.addAttribute("topos", toposForView);
        model.addAttribute("savedTopo", "no");
        model.addAttribute("currentPage", page);
        return "personalSpace";
    }

    @PostMapping("/mon-compte{page}")
    public String post(HttpServletRequest request, Model model,
                       @ModelAttribute Topo topo,
                       @RequestParam(value = "page", required = false, defaultValue = "1") int page
                        ) {
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
        if (!sesService.checkUserCanDoThisRequest(request, "connect"))
            return sesService.redirectToErrorPage(request);

        //******************************** Service normal ***************************************
        if (topoService.existsByName(topo.getName())) {
            model.addAttribute("savedTopo", "error");
        } else {
            if (topo.getAvailable().equals("null")) topo.setAvailable("false");
            else {
                topo.setAvailable("true");
                topo.setReserved("false");
                topo.setBeingReserved("false");
            }
            topoService.saveTopo(topo);
            model.addAttribute("savedTopo", "good");
        }

        Iterable<Topo> toposForView = topoService.getToposByAuthorId(Long.toString(u.getId()));
        var nbTopo = ((Collection<?>) toposForView).size();

        Map<String, Integer> parameters = Map.of(   "currentPage", page,
                                                    "elementNumber", nbTopo,
                                                    "maxElementByPage", maxElementByPage);
        List<Integer> listPage = utils.getListPage(parameters);

        var nbTopoToTruncate = (page-1) * maxElementByPage;
        if (page > 1) toposForView = ((List<Topo>) toposForView).stream().skip(nbTopoToTruncate).limit(maxElementByPage).toList();
        else toposForView = ((List<Topo>) toposForView).stream().limit(maxElementByPage).toList();

        model.addAttribute("listPage", listPage);
        model.addAttribute("topos", toposForView);
        model.addAttribute("currentPage", page);

        return "personalSpace";
    }

    @GetMapping("/mon-compte-demande-accepte{idTopo, page}")
    public String reservingTopo( Model model, HttpServletRequest request,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "idTopo", required = true, defaultValue = "0") int idTopo) {
        var s = sesService.getRequestStarter(request, currentUrl);

////////////
        User u = userService.getUserById(1L).get();
        model.addAttribute("user", u);
////////////


        if(topoService.getTopoById((long)idTopo).isPresent()) {
            var topo = topoService.getTopoById((long) idTopo).get();
            topo.setAvailable("false");
            topo.setBeingReserved("true");
            topo.setReserved("true");
            topo.setOwnerId(s.getU().getId()+"");
            topoService.saveTopo(topo);
            log.info("topo save = "+topo.toString());
        }

        Iterable<Topo> allTopoAvailable = topoService.getToposByAuthorId(Long.toString(u.getId()));
        var numberTopo = ((Collection<?>) allTopoAvailable).size();

        if (sesService.pageAskedIsCorrect(1, numberTopo / maxElementByPage)) return sesService.redirectToErrorPage(request);

        model.addAttribute("listPage", utils.getListPage(1, numberTopo, maxElementByPage));
        model.addAttribute("topos", utils.truncateIterableByParameters(1, maxElementByPage, allTopoAvailable));
        model.addAttribute("currentPage", page);

        return currentUrl;
    }

}

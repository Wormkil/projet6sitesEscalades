package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Topo;
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
import java.util.Collection;

@Controller
public class TopoController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final String currentUrl = "topos";
    private final int maxElementByPage = 10;

    @Autowired
    private SessionService sesService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopoService topoService;
    @Autowired
    private UtilitairesService utils;

    @GetMapping("/topos{page}")
    public String getAllTopos(HttpServletRequest request, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        sesService.getRequestStarter(request, currentUrl);

        Iterable<Topo> allTopoAvailable = topoService.getTopoRepository().findByAvailable("true");
        var numberTopo = ((Collection<?>) allTopoAvailable).size();

        if (sesService.pageAskedIsCorrect(page, numberTopo / maxElementByPage)) return sesService.redirectToErrorPage(request);

        model.addAttribute("allTopo", utils.truncateIterableByParameters(page, maxElementByPage, allTopoAvailable));
        model.addAttribute("listPage", utils.getListPage(page, numberTopo, maxElementByPage));
        model.addAttribute("currentPage", page);

        return currentUrl;
    }
    @GetMapping("/topos-disponible{page}")
    public String getToposAvailable(HttpServletRequest request, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        sesService.getRequestStarter(request, currentUrl);

        Iterable<Topo> allTopoAvailable = topoService.getTopoRepository().findByAvailable("true");
        var numberTopo = ((Collection<?>) allTopoAvailable).size();

        if (sesService.pageAskedIsCorrect(page, numberTopo / maxElementByPage)) return sesService.redirectToErrorPage(request);

        model.addAttribute("allTopo", utils.truncateIterableByParameters(page, maxElementByPage, allTopoAvailable));
        model.addAttribute("listPage", utils.getListPage(page, numberTopo, maxElementByPage));
        model.addAttribute("currentPage", page);

        return currentUrl;
    }
    @GetMapping("/topos-indisponible{page}")
    public String getToposNotAvailable(HttpServletRequest request, Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        sesService.getRequestStarter(request, currentUrl);

        Iterable<Topo> allTopoAvailable = topoService.getTopoRepository().findByAvailable("false");
        var numberTopo = ((Collection<?>) allTopoAvailable).size();

        if (sesService.pageAskedIsCorrect(page, numberTopo / maxElementByPage)) return sesService.redirectToErrorPage(request);

        model.addAttribute("allTopo", utils.truncateIterableByParameters(page, maxElementByPage, allTopoAvailable));
        model.addAttribute("listPage", utils.getListPage(page, numberTopo, maxElementByPage));
        model.addAttribute("currentPage", page);

        return currentUrl;
    }

    @GetMapping("/topos-réservé{idTopo}")
    public String reservingTopo( Model model, HttpServletRequest request, @RequestParam(value = "idTopo", required = true, defaultValue = "0") int idTopo){
        var s = sesService.getRequestStarter(request, currentUrl);

        if(topoService.getTopoById((long)idTopo).isPresent()) {
            var topo = topoService.getTopoById((long) idTopo).get();
            topo.setAvailable("false");
            topo.setBeingReserved("true");
            topo.setOwnerId(s.getU().getId()+"");
            topoService.saveTopo(topo);
            log.info("topo save = "+topo.toString());
        }

        Iterable<Topo> allTopoAvailable = topoService.getTopoRepository().findByAvailable("true");
        var numberTopo = ((Collection<?>) allTopoAvailable).size();

        if (sesService.pageAskedIsCorrect(1, numberTopo / maxElementByPage)) return sesService.redirectToErrorPage(request);

        model.addAttribute("allTopo", utils.truncateIterableByParameters(1, maxElementByPage, allTopoAvailable));
        model.addAttribute("listPage", utils.getListPage(1, numberTopo, maxElementByPage));
        model.addAttribute("currentPage", 1);

        return currentUrl;
    }
}

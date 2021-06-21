package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.model.Comment;
import fr.oc.amisdelescalade.service.ClimbSitesService;
import fr.oc.amisdelescalade.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ClimginSiteController {


    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private ClimbSitesService csService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping("/modifié-site-escalade{csId}")
    public String changeClimbingSite(Model model, HttpServletRequest request, @ModelAttribute Comment com, @RequestParam(value = "csId", required = false) Long csId) {
        HttpSession session = sessionService.OpenOrGetSession(request);
        ClimbSites cs;
        if (csId == 0) {
            cs = new ClimbSites();
            model.addAttribute("cs", cs);
            model.addAttribute("csID", 0);
            log.info("je suis passé par la");
            log.info(model.getAttribute("csID").toString());
        }
        else {
            cs = csService.getCSById(csId).get();
            model.addAttribute("cs", cs);

            if (cs.getProfile().contains("Dévers"))
                model.addAttribute("devers", "true");
            if (cs.getProfile().contains("Vertical"))
                model.addAttribute("vertical", "true");
            if (cs.getProfile().contains("Dalle"))
                model.addAttribute("dalle", "true");

            if (cs.getPlugType().contains("Bacs"))
                model.addAttribute("bac", "true");
            if (cs.getPlugType().contains("Mini-bacs"))
                model.addAttribute("minibac", "true");
            if (cs.getPlugType().contains("Poignées"))
                model.addAttribute("poignee", "true");
            if (cs.getPlugType().contains("Réglettes"))
                model.addAttribute("reglette", "true");
            if (cs.getPlugType().contains("Trous"))
                model.addAttribute("trou", "true");
            if (cs.getPlugType().contains("Plats") || cs.getPlugType().contains("Aplats"))
                model.addAttribute("plat", "true");
            if (cs.getPlugType().contains("Bossettes"))
                model.addAttribute("bossette", "true");
            if (cs.getPlugType().contains("Pinces"))
                model.addAttribute("pince", "true");
            if (cs.getPlugType().contains("Colonnes"))
                model.addAttribute("colonne", "true");
            if (cs.getPlugType().contains("Boules"))
                model.addAttribute("boule", "true");
            if (cs.getPlugType().contains("Volumes"))
                model.addAttribute("volume", "true");
            if (cs.getPlugType().contains("Fissures"))
                model.addAttribute("fissure", "true");

            if (cs.getStoneType().contains("Grès"))
                model.addAttribute("gres", "true");
            if (cs.getStoneType().contains("Granite"))
                model.addAttribute("granite", "true");
            if (cs.getStoneType().contains("Craie"))
                model.addAttribute("craie", "true");
            if (cs.getStoneType().contains("Gneiss"))
                model.addAttribute("gneiss", "true");
            if (cs.getStoneType().contains("Ardoise"))
                model.addAttribute("ardoise", "true");
            if (cs.getStoneType().contains("Calcaire"))
                model.addAttribute("calcaire", "true");

            if (cs.getEquipment().contains("Sportif"))
                model.addAttribute("sportif", "true");
            if (cs.getEquipment().contains("Engagé"))
                model.addAttribute("engage", "true");
            if (cs.getEquipment().contains("Terrain d'aventure"))
                model.addAttribute("terrain", "true");

            if (cs.getBestSeason().contains("Automne"))
                model.addAttribute("automne", "true");
            if (cs.getBestSeason().contains("Hiver"))
                model.addAttribute("hiver", "true");
            if (cs.getBestSeason().contains("Été"))
                model.addAttribute("ete", "true");
            if (cs.getBestSeason().contains("Printemps"))
                model.addAttribute("printemps", "true");

            if (cs.getOrientations().contains("S"))
                model.addAttribute("s", "true");
            if (cs.getOrientations().contains("E"))
                model.addAttribute("e", "true");
            if (cs.getOrientations().contains("N"))
                model.addAttribute("n", "true");
            if (cs.getOrientations().contains("O"))
                model.addAttribute("o", "true");

        }
        return "changeCs";

    }

    @RequestMapping("/changement-site-escalade-sauvegardé")
    public String saveChangeClimbingSite(HttpServletRequest request,
                                         @ModelAttribute ClimbSites cs,
                                         @RequestParam(value = "toggle", required = false) String toggle,
                                         @RequestParam(value = "profile", required = false) String profile,
                                         @RequestParam(value = "plugtype", required = false) String plugtype,
                                         @RequestParam(value = "stonetype", required = false) String stonetype,
                                         @RequestParam(value = "equipment", required = false) String equipment,
                                         @RequestParam(value = "bestsaison", required = false) String bestsaison,
                                         @RequestParam(value = "orientation", required = false) String orientation) {

        HttpSession session = sessionService.OpenOrGetSession(request);
        if (cs.getId() != 0L) {
            cs.setId(Long.parseLong(session.getAttribute("currentCsId").toString()));
        }
        else {
            //Si on est en train de créer un nouveau site, on affecte au site l'id de l'utilisateur courant
            cs.setAuthorId(session.getAttribute("userId").toString());
        }
        cs.setProfile(profile);
        cs.setPlugType(plugtype);
        cs.setStoneType(stonetype);
        cs.setEquipment(equipment);
        cs.setBestSeason(bestsaison);
        cs.setOrientations(orientation);
        cs.setPathImages("../images/"+cs.getName()+"/");
        if (toggle == null) cs.setOfficial("false"); else cs.setOfficial("true");

        csService.saveCS(cs);

        return "index";
    }
}

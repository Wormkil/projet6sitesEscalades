package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.model.Comment;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.ClimbSitesService;
import fr.oc.amisdelescalade.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SitesEscaladesController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private ClimbSitesService csService;
    @Autowired
    private CommentService comService;

    @PostMapping("/site-escalade")
    public String index(Model model, HttpServletRequest request, @ModelAttribute Comment com) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);

        //if(session.getAttribute("user") != null) currentUserId = session.getAttribute("user");
        Long csId = Long.parseLong(request.getParameter("cs_id"));
        session.setAttribute("currentCsId", csId);

        ClimbSites cs = csService.getCSById(csId).get();
        model.addAttribute("name", cs.getName());
        model.addAttribute("description", cs.getDescription());
        model.addAttribute("access", cs.getAccess());
        model.addAttribute("region", cs.getRegion());
        model.addAttribute("country", cs.getCountry());
        model.addAttribute("orientations", cs.getOrientations());
        model.addAttribute("bestSeason", cs.getBestSeason());
        model.addAttribute("cotationRange", cs.getCotationsRange());
        model.addAttribute("nbRoute", cs.getNbRoute());
        model.addAttribute("equipement", cs.getEquipment());
        model.addAttribute("maxHeight", cs.getMaxHeight());
        model.addAttribute("stoneType", cs.getStoneType());
        model.addAttribute("profile", cs.getProfile());
        model.addAttribute("plugType", cs.getPlugType());
        model.addAttribute("infosSup", cs.getInfoSup());
        model.addAttribute("pathImages", cs.getPathImages());
        model.addAttribute("urlIframe", cs.getUrlggmaps());

        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        model.addAttribute("coms", coms);
        List<User> usersName = comService.getUserOfComment(coms);
        model.addAttribute("comsUsers", usersName);

        return "site-escalade";
    }

    @PostMapping("/site-escalade-commentaire-ajouté")
    public String addComment(Model model, HttpServletRequest request, @ModelAttribute Comment com) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);

        com.setCsId(Long.parseLong(session.getAttribute("currentCsId").toString()));
        com.setUserId(Long.parseLong(session.getAttribute("userId").toString()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        com.setCreationDate(dateFormat.format(date));
        comService.saveCom(com);

        ClimbSites cs = csService.getCSById(com.getCsId()).get();
        model.addAttribute("name", cs.getName());
        model.addAttribute("description", cs.getDescription());
        model.addAttribute("access", cs.getAccess());
        model.addAttribute("region", cs.getRegion());
        model.addAttribute("country", cs.getCountry());
        model.addAttribute("orientations", cs.getOrientations());
        model.addAttribute("bestSeason", cs.getBestSeason());
        model.addAttribute("cotationRange", cs.getCotationsRange());
        model.addAttribute("nbRoute", cs.getNbRoute());
        model.addAttribute("equipement", cs.getEquipment());
        model.addAttribute("maxHeight", cs.getMaxHeight());
        model.addAttribute("stoneType", cs.getStoneType());
        model.addAttribute("profile", cs.getProfile());
        model.addAttribute("plugType", cs.getPlugType());
        model.addAttribute("infosSup", cs.getInfoSup());
        model.addAttribute("pathImages", cs.getPathImages());
        model.addAttribute("urlIframe", cs.getUrlggmaps());

        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        model.addAttribute("coms", coms);
        List<User> usersName = comService.getUserOfComment(coms);
        model.addAttribute("comsUsers", usersName);

        return "site-escalade";
    }
}

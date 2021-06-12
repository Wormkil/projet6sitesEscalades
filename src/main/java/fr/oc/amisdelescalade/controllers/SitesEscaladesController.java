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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("cs", cs);
        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        model.addAttribute("coms", coms);
        List<User> usersName = comService.getUserOfComment(coms);
        model.addAttribute("comsUsers", usersName);

        return "site-escalade";
    }

    @PostMapping("/site-escalade/commentaire-ajouté")
    public String addComment(Model model, HttpServletRequest request, @ModelAttribute Comment com) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        com.setCreationDate(dateFormat.format(date));
        com.setCsId(Long.parseLong(session.getAttribute("currentCsId").toString()));
        com.setUserId(Long.parseLong(session.getAttribute("userId").toString()));
        comService.saveCom(com);

        ClimbSites cs = csService.getCSById(Long.parseLong(session.getAttribute("currentCsId").toString())).get();
        model.addAttribute("cs", cs);
        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        model.addAttribute("coms", coms);
        List<User> usersName = comService.getUserOfComment(coms);
        model.addAttribute("comsUsers", usersName);

        return "site-escalade";
    }

    @RequestMapping("/site-escalade-commentaire-supprimé{idCom}")
    public String supprComment(Model model, HttpServletRequest request, @ModelAttribute Comment com, @RequestParam(value = "id", required = false) Long id) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);

        //
        log.info(id.toString());
        comService.deleteCom(id);
        //

        ClimbSites cs = csService.getCSById(Long.parseLong(session.getAttribute("currentCsId").toString())).get();
        model.addAttribute("cs", cs);
        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        model.addAttribute("coms", coms);
        List<User> usersName = comService.getUserOfComment(coms);
        model.addAttribute("comsUsers", usersName);

        return "site-escalade";
    }

    @PostMapping("/site-escalade-commentaire-modifié{idCom}")
    public String changeComment(Model model, HttpServletRequest request, @ModelAttribute Comment com, @RequestParam(value = "id", required = false) Long id){
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        Comment comTmp = comService.getComById(id).get();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        comTmp.setContent(com.getContent()+" (Commentaire modifié par "+session.getAttribute("userName")+" le "+dateFormat.format(date)+")");

        log.info(com.toString());
        comService.saveCom(comTmp);

        ClimbSites cs = csService.getCSById(Long.parseLong(session.getAttribute("currentCsId").toString())).get();
        model.addAttribute("cs", cs);
        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        model.addAttribute("coms", coms);
        List<User> usersName = comService.getUserOfComment(coms);
        model.addAttribute("comsUsers", usersName);


        return "site-escalade";
    }
}


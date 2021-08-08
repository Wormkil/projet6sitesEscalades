package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.model.Comment;
import fr.oc.amisdelescalade.model.SessionWithUser;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class DisplayCSController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final String currentUrl = "site-escalade";
    private final int maxElementByPage = 5;

    @Autowired
    private ClimbSitesService csService;
    @Autowired
    private CommentService comService;
    @Autowired
    private SessionService sesService;
    @Autowired
    private UserService userService;
    @Autowired
    private UtilitairesService utils;

    @GetMapping("/site-escalade{csId, page}")
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "csId", required = false, defaultValue = "1") Long csId,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        SessionWithUser su = sesService.getRequestStarter(request, currentUrl);

        if (csService.getCSById(csId).isEmpty()) return sesService.redirectToErrorPage(request);

        ClimbSites cs = csService.getCSById(csId).get();
        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        var numberComs = ((Collection<?>) coms).size();
        coms = utils.truncateIterableByParameters(page, maxElementByPage, coms);

        if (sesService.pageAskedIsCorrect(page, numberComs / maxElementByPage)) return sesService.redirectToErrorPage(request);

        model.addAttribute("cs", cs);
        model.addAttribute("coms", coms);
        model.addAttribute("currentPage", page);
        model.addAttribute("listPage", utils.getListPage(page, numberComs, maxElementByPage));
        model.addAttribute("comsUsers", comService.getUserOfComment(coms));
        model.addAttribute("editRight", "false");
        if (su.getU() != null && ((sesService.isGuestInSessionOfficial(su.getS())) || (Long.toString(su.getU().getId()).equals(cs.getAuthorId())))) {
            model.addAttribute("editRight", "true");
        }

        return currentUrl;
    }


    @PostMapping("/site-escalade{csId, page}")
    public String addComment(Model model, HttpServletRequest request, @ModelAttribute Comment com,
                             @RequestParam(value = "csId", required = false, defaultValue = "1") Long csId,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        HttpSession session = sesService.OpenOrGetSession(request);

        //*************************** Verification Droits d'accès Utilisateur *******************
        User u = sesService.getUserFromSession(session);
        //On vérifie qu'il y ait bien un utilisateur connecté sinon on redirige vers une page d'erreur
        if(u == null)
            return sesService.redirectToErrorPage(request);
        //On vérifie que l'utilisateur est bien un 'official' sinon on redirige vers une page d'érreur
        if (!sesService.checkUserCanDoThisRequest(request, "connect"))
            return sesService.redirectToErrorPage(request);


        //*************************** Spécificité de la requête - Sauvegarde du nouveau ommentaire *******************
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        com.setCreationDate(dateFormat.format(date));
        com.setCsId(csId);
        com.setUserId(u.getId());
        comService.saveCom(com);

        //*************************** Calcule et Récupération de donné, envoyé côté Vue *******************
        ClimbSites cs = csService.getCSById(csId).get();
        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        var numberComs = ((Collection<?>) coms).size();
        if ((page > (numberComs / maxElementByPage ) + 1 && page != 1) || page <= 0) {
            return sesService.redirectToErrorPage(request);
        }

        Map<String, Integer> parameters = Map.of("currentPage", page, "elementNumber", numberComs, "maxElementByPage", maxElementByPage);
        List<Integer> listPage = utils.getListPage(parameters);

        //coms = utils.truncateIterableByParameters(parameters, coms);

        var nbComsToTruncate = (page-1) * maxElementByPage;
        if (page > 1) coms = ((List<Comment>) coms).stream().skip(nbComsToTruncate).limit(maxElementByPage).toList();
        else coms = ((List<Comment>) coms).stream().limit(maxElementByPage).toList();

        List<User> usersName = comService.getUserOfComment(coms);
        
        model.addAttribute("canChange", "false");
        if ((sesService.isGuestInSessionOfficial(session)) || (Long.toString(u.getId()).equals(cs.getAuthorId()))) {
            model.addAttribute("canChange", "true");
        }

        model.addAttribute("cs", cs);
        model.addAttribute("coms", coms);
        model.addAttribute("listPage", listPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("comsUsers", usersName);

        return "site-escalade";

    }

    @RequestMapping("/site-escalade-commentaire-supprimé{idCom}")
    public String supprComment(Model model, HttpServletRequest request, @ModelAttribute Comment com, @RequestParam(value = "id", required = false) Long id) {
        HttpSession session = sesService.OpenOrGetSession(request);


        comService.deleteCom(id);


        ClimbSites cs = csService.getCSById(id).get();
        model.addAttribute("cs", cs);
        Iterable<Comment> coms = comService.findCommentByCsId(cs.getId());
        model.addAttribute("coms", coms);
        List<User> usersName = comService.getUserOfComment(coms);
        model.addAttribute("comsUsers", usersName);

        return "site-escalade";
    }

    @PostMapping("/site-escalade-commentaire-modifié{idCom}")
    public String changeComment(Model model, HttpServletRequest request, @ModelAttribute Comment com, @RequestParam(value = "id", required = false) Long id){
        HttpSession session = sesService.OpenOrGetSession(request);
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


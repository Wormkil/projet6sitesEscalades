package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Comment;
import fr.oc.amisdelescalade.service.CommentService;
import fr.oc.amisdelescalade.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChangeComController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private CommentService comService;
    @Autowired
    private UserService userService;

    @RequestMapping("/modifié-un-commentaire{idCom}")
    public String indexChangeComment(Model model, @RequestParam(value = "id", required = false) Long id) {
       Comment com = comService.getComById(id).get();
       model.addAttribute("com", com);
       String userName = userService.getUserById(com.getUserId()).get().getUserName();
       model.addAttribute("userName", userName);
        return "changeCom";
    }

}

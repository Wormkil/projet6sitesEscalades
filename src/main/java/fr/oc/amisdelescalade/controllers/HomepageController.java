package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomepageController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    private String currentPage = "index";

    @Autowired
    private SessionService sesService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        HttpSession session = sesService.OpenOrGetSession(request);
        sesService.saveLastPage(session, currentPage);

        //log.info(sesService.getUserFromSession(session).toString());

        return currentPage;
    }
}

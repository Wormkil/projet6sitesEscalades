package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomepageController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final String currentUrl = "index";


    @Autowired
    private SessionService sesService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        sesService.getRequestStarter(request, currentUrl);
        return currentUrl;
    }
}

package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.service.AccountService;
import fr.oc.amisdelescalade.service.SessionService;
import fr.oc.amisdelescalade.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private AccountService accService;
    @Autowired
    private UserService uService;
    @Autowired
    private SessionService sesService;

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request) {
        HttpSession session = sesService.OpenOrGetSession(request);

        if (sesService.getUserFromSession(session) == null) {
            return "login";
        }
        else return "errorPage";

    }

    @PostMapping("/login")
    public String loginSubmit(HttpServletRequest request, Model model, HttpSession session) {


        //Est-ce il a le droit de faire la requete (par exemple il est bien connecté)

        //Est-ce il a le droit de faire sa (la requete) ?

        // Si oui j'execute le service


        String pEmail = request.getParameter("email");
        String pPassword = request.getParameter("password");
        model.addAttribute("defaultEmail", pEmail);
        model.addAttribute("defaultPassword", pPassword);

        Map<String,String> mapError = accService.canConnect(pEmail, pPassword);
        if (mapError.isEmpty()) {
            accService.connectUser(request, uService.getUserByEmail(pEmail).get());
            return sesService.getLastPage(session);
        }
        else {
            if (mapError.containsKey("errorPass")) {
                model.addAttribute("errorPass", mapError.get("errorPass"));
                model.addAttribute("defaultPassword", "");
            }
            if (mapError.containsKey("errorEmail")) {
                model.addAttribute("errorMail", mapError.get("errorEmail"));
                model.addAttribute("defaultEmail", "");
            }
            return "login";
        }
    }

    @GetMapping("/logOut")
    public String logOut( HttpServletRequest request) {
        HttpSession session = sesService.OpenOrGetSession(request);
        session.setAttribute( "user", null);

        return "index";
    }
}
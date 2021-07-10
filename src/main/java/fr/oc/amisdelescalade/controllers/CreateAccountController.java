package fr.oc.amisdelescalade.controllers;


import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.SessionWithUser;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.service.AccountService;
import fr.oc.amisdelescalade.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CreateAccountController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private final String currentUrl = "creer-un-compte";

    @Autowired
    private AccountService accService;
    @Autowired
    private SessionService sesService;

    @GetMapping("/creer-un-compte")
    public String createAccountForm(HttpServletRequest request) {
        SessionWithUser su = sesService.getRequestStarter(request, currentUrl);
        return currentUrl;
    }

    //Est-ce il a le droit de faire la requete (par exemple il est bien connecté)
    //Est-ce il a le droit de faire sa (la requete) ?
    // Si oui j'execute le service

    @PostMapping(value = "/createAccount")
    public String createAccountSubmit(HttpServletRequest request, Model model, @ModelAttribute("user") User user) {

        //Sauvegarde des données dans le model pour réafficher les entrés de l'utilisateur en cas d'érreur
        model.addAttribute("defaultFullName", user.getFullName());
        model.addAttribute("defaultUserName", user.getUserName());
        model.addAttribute("defaultCountry", user.getCountry());
        model.addAttribute("defaultEmail", user.getEmail());
        model.addAttribute("defaultPassword", user.getPassword());
        model.addAttribute("defaultPasswordConfirm", user.getPasswordConfirm());

        //Est-ce il a le droit de faire la requete (par exemple il est bien connecté)
        //Est-ce il a le droit de faire sa (la requete) ?

        Map<String,String> mapError = accService.canRegister(user);
        if (!mapError.isEmpty()) {
            if (mapError.containsKey("errorConfirmPass")){
                model.addAttribute("errorConfirmPass", mapError.get("errorConfirmPass"));
                model.addAttribute("defaultPasswordConfirm", "");
            }
            if (mapError.containsKey("errorMail")){
                model.addAttribute("errorMail", mapError.get("errorMail"));
                model.addAttribute("defaultEmail", "");
            }
            if (mapError.containsKey("errorUserName")){
                model.addAttribute("errorUserName", mapError.get("errorUserName"));
                model.addAttribute("defaultUserName", "");
            }
            return "createAccount";
        }

        // Si oui j'execute le service
        accService.registerUser(user);
        accService.connectUser(request, user);
        return "index";

    }
}

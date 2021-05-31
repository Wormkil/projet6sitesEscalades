package fr.oc.amisdelescalade.controllers;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.service.AccountService;
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

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        if (session.getAttribute("user") != null) return "login";
        else {
            session.setAttribute("user", null);
            return "login";
        }
    }

    @PostMapping("/login")
    public String loginSubmit(HttpServletRequest request, Model model) {


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
            return "index";
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





       /* HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);

        String pEmail = request.getParameter("email");
        String pPassword = request.getParameter("password");
        model.addAttribute("defaultEmail", pEmail);
        model.addAttribute("defaultPassword", pPassword);
        model.addAttribute("user", new User());

        String emailFromBD;
        String sql = "SELECT email FROM Users WHERE email = '"+pEmail+"'";
        try {
            emailFromBD = jdbcTemplate.queryForObject(sql, String.class);
        }catch (DataAccessException e){
            //emailFromBD = "null";
            session.setAttribute( "errorMail", "L'adresse email n'existe pas ou est mal orthographiée. " +
                    "Veuillez vérifier l'orthographe ou vous créer un compte.");
            session.setAttribute( "errorPass", null);
            return "login";
        }

        // Il a trouvé un email, maintenant on verifie que le mot de passe correspond
        String passwordFromBD;
        sql = "SELECT password FROM Users WHERE email = '"+pEmail+"' AND password = '"+pPassword+"'";
        try {
            passwordFromBD = jdbcTemplate.queryForObject(sql, String.class);
        }catch (DataAccessException e){
            //passwordFromBD = "null";
            session.setAttribute( "errorPass", "Le mot de passe ne correspond pas à l'adresse email donnée ou est " +
                    "mal orthographiée. Veuillez vérifier l'orthographe du mot de passe.");
            session.setAttribute( "errorMail", null);
            return "login";
        }

        sql = "SELECT * FROM Users WHERE email = '"+pEmail+"'";
        List<User> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        session.setAttribute( "user", user.get(0));

        return "index";*/
    }

    @GetMapping("/logOut")
    public String logOut( HttpServletRequest request) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        session.setAttribute( "user", null);

        return "index";
    }
}
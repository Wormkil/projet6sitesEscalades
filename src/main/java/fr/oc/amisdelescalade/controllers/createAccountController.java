package fr.oc.amisdelescalade.controllers;


import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.repository.user.UserRepository;
import fr.oc.amisdelescalade.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class createAccountController {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accService;// A changer pour le service qui va

    @GetMapping("/createAccount")
    public String createAccountForm(HttpServletRequest request, Model model) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        session.setAttribute( "errorConfirmPass", null);
        session.setAttribute( "errorMail", null);
        session.setAttribute( "errorUserName", null);
        session.setAttribute( "errorConfirmPass", null);

        model.addAttribute("user", new User());

        return "createAccount";
    }

    //Est-ce il a le droit de faire la requete (par exemple il est bien connecté)
    //Est-ce il a le droit de faire sa (la requete) ?
    // Si oui j'execute le service




    @PostMapping(value = "/createAccount", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createAccountSubmit( Model model, @ModelAttribute User user) {
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        model.addAttribute("user", new User());
        //Est-ce il a le droit de faire la requete (par exemple il est bien connecté)
        //Est-ce il a le droit de faire sa (la requete) ?
        accService.connectUser(user.getEmail(),user.getPassword());

        return "createAccount";


        // Si oui j'execute le service

        // Je veut savoir si une adresse mail contenu dans une variable existe ou pas dans ma base.


        /*
        try {

            userRepository.save(user);
        }catch(IllegalArgumentException e){
            var maybeUser = userRepository.findByEmail(user.getEmail());


        } */



        //model.addAttribute( "errorMail", "L'adresse email est déjà prises par un de nos utilisateur. Veuillez" +
                    //" en choisir une autre.");







        /*
        // On récupère les données entré dans le formulaire de création de compte
        String pFullName = request.getParameter("fullName");
        String pUserName = request.getParameter("userName");
        String pCountry = request.getParameter("country");
        pEmail = request.getParameter("email");
        String pPassword = request.getParameter("password");
        String pPasswordConfirm = request.getParameter("passwordConfirm");
        String pLocalDate = (LocalDate.now()).format(DateTimeFormatter.ofPattern("uuuu-MM-dd"));

        //Sauvegarde des données dans le model pour réafficher les entrés de l'utilisateur en cas d'érreur
        model.addAttribute("user", new User());
        model.addAttribute("defaultFullName", pFullName);
        model.addAttribute("defaultUserName", pUserName);
        model.addAttribute("defaultCountry", pCountry);
        model.addAttribute("defaultEmail", pEmail);
        model.addAttribute("defaultPassword", pPassword);
        model.addAttribute("defaultPasswordConfirm", pPasswordConfirm);


        //Vérifie sans appel à  la bado si les deux mot de passe correspondent, sinon on affiche une erreur
        if (!pPassword.equals(pPasswordConfirm)) {
            model.addAttribute("errorConfirmPass", "Les mots de passes ne correspondent pas. Veuillez" +
                    " verifier leurs orthographes.");
            model.addAttribute("errorUserName", null);
            model.addAttribute("errorMail", null);
            return "createAccount";
        }
        //Réinitialisation du message d'erreur car on a passé le if.
        model.addAttribute( "errorConfirmPass", null);

        Boolean haveErrors = false;
        //Iterable<User> users = userController.getUsers();

        Optionnal<User> user = userController.getUsers();

        for (User u : users){
            if (u.getEmail().equals(pEmail)) {
                model.addAttribute( "errorMail", "L'adresse email est déjà prises par un de nos utilisateur. Veuillez" +
                        " en choisir une autre.");
                haveErrors = true;
            }
            if (u.getUserName().equals(pUserName)) {
                model.addAttribute( "errorUserName", "Le pseudo est déjà pris par un de nos utilisateur. Veuillez" +
                        " en choisir un autre.");
                haveErrors = true;
            }
        }

        if (haveErrors) {
            return "createAccount";
        }
        else {



        }*/

       /* String pFullName = request.getParameter("fullName");
        String pUserName = request.getParameter("userName");
        String pCountry = request.getParameter("country");
        String pEmail = request.getParameter("email");
        String pPassword = request.getParameter("password");
        String pPasswordConfirm = request.getParameter("passwordConfirm");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        String pLocalDate = (LocalDate.now()).format(dtf);

        model.addAttribute("user", new User());
        model.addAttribute("defaultFullName", pFullName);
        model.addAttribute("defaultUserName", pUserName);
        model.addAttribute("defaultCountry", pCountry);
        model.addAttribute("defaultEmail", pEmail);
        model.addAttribute("defaultPassword", pPassword);
        model.addAttribute("defaultPasswordConfirm", pPasswordConfirm);

        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);

        if (!pPassword.equals(pPasswordConfirm)) {
            session.setAttribute("errorConfirmPass", "Les mots de passes ne correspondent pas. Veuillez" +
                    " verifier leurs orthographes.");
            session.setAttribute("errorUserName", null);
            session.setAttribute("errorMail", null);
            return "createAccount";
        }
        session.setAttribute( "errorConfirmPass", null);

        String emailFromBD;
        String sql = "SELECT email FROM Users WHERE email = '"+pEmail+"'";
        try {
            emailFromBD = jdbcTemplate.queryForObject(sql, String.class);
        }catch (DataAccessException e){
            emailFromBD = "null";
        }

        String userNameFromBD;
        sql = "SELECT userName FROM Users WHERE userName = '"+pUserName+"'";
        try {
            userNameFromBD = jdbcTemplate.queryForObject(sql, String.class);
        }catch (DataAccessException e){
            userNameFromBD = "null";
        }

        if (!emailFromBD.equals(pEmail) && !userNameFromBD.equals(pUserName)) {
            sql = "INSERT INTO Users(email, password, fullName, userName, country, creationAccount, officialMember)" +
                    " VALUES ('"+pEmail+"', '"+pPassword+"', '"+pFullName+"', '"+pUserName+"', '"+pCountry+"', '"+pLocalDate+"', '0')";
            jdbcTemplate.batchUpdate(sql);

            User userCreated = new User();
            userCreated.setCreationAccount(pLocalDate);
            userCreated.setEmail(pEmail);
            userCreated.setPassword(pPassword);
            userCreated.setPasswordConfirm(pPasswordConfirm);
            userCreated.setFullName(pFullName);
            userCreated.setUserName(pUserName);
            userCreated.setCountry(pCountry);
            userCreated.setOfficialMember(false);
            session.setAttribute( "user", userCreated);
            session.setAttribute( "userName", pUserName);
            session.setAttribute( "errorConfirmPass", null);
            session.setAttribute( "errorMail", null);
            session.setAttribute( "errorUserName", null);

            return "index";

        } else if (emailFromBD.equals(pEmail) && !userNameFromBD.equals(pUserName)){
            session.setAttribute( "errorMail", "L'adresse email est déjà prises par un de nos utilisateur. Veuillez" +
                    " en choisir une autre.");
            session.setAttribute( "errorUserName", null);
            return "createAccount";
        } else if (!emailFromBD.equals(pEmail) && userNameFromBD.equals(pUserName)){
            session.setAttribute( "errorUserName", "Le pseudo est déjà pris par un de nos utilisateur. Veuillez" +
                    " en choisir un autre.");
            session.setAttribute( "errorMail", null);
            return "createAccount";
        } else {
            session.setAttribute( "errorMail", "L'adresse email est déjà prises par un de nos utilisateur. Veuillez" +
                    " en choisir une autre.");
            session.setAttribute( "errorUserName", "Le pseudo est déjà pris par un de nos utilisateur. Veuillez" +
                    " en choisir un autre.");
            return "createAccount";
        }*/
    }
}

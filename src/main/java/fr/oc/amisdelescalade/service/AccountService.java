package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private UserService userService;

    public User registerUser(User user) {
        //Ajoute la date actuel au nouveau utilisateur enregistré
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        user.setCreationAccount(dateFormat.format(date));
        return userService.saveUser(user);

    }

    public Map<String, String> canRegister(User user){
        Map<String,String> mapError = new HashMap<String,String>();

        // On check la correspondance des mot de passe
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            mapError.put("errorConfirmPass", "Les mots de passes ne correspondent pas. Veuillez" +
                    " verifier leurs orthographes.");
            log.info("Erreur mdp - Difference between Mdp/mdpconfirm");
        }

        // On check l'existence de l'email
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            mapError.put( "errorMail", "L'adresse email est déjà prises par un de nos utilisateur. Veuillez" +
                    " en choisir une autre.");
            log.info("Erreur email - Same email");
        }

        // On check l'existence de l'userName
        if (userService.getUserByUserName(user.getUserName()).isPresent()) {
            mapError.put( "errorUserName", "Le pseudo est déjà pris par un de nos utilisateur. Veuillez" +
                    " en choisir un autre.");
            log.info("Erreur userName - Same userName");
        }

        return mapError;
    }
    public Map<String, String> canConnect(String email, String password) {
        Map<String, String> mapError = new HashMap<String, String>();

        if (!userService.getUserByEmail(email).isEmpty()) {
            if (!userService.getUserByEmail(email).get().getPassword().equals(password)) {
                log.info("Erreur pass");
                mapError.put("errorPass", "Le mot de passe ne correspond avec l'adresse mail.");
            }
            return mapError;
        } else {
            mapError.put("errorEmail", "L'adresse Email n'est pas présente dans la base de donnée des utilisateurs");
            log.info("Erreur mail");
            return mapError;
        }
    }

    public void connectUser(HttpServletRequest request, User user){
        HttpSession session = Projet6Application.sessionManager.OpenOrGetSession(request);
        session.setAttribute("user", user);
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("userId", user.getId());
        session.setAttribute("userOfficial", user.getOfficialMember());
    }
}

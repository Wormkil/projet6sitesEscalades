package fr.oc.amisdelescalade.utils;

import fr.oc.amisdelescalade.Projet6Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    public HttpSession OpenOrGetSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.info("Aucune session trouvé : Nouvelle session créer");
            return request.getSession();
        }else {
            log.info("Ancienne session trouvé : Ancienne session récupérer");
            return session;
        }
    }

}

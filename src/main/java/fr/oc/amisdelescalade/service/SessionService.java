package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Data
@Service
public class SessionService {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    public HttpSession OpenOrGetSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.info("Nouvelle session créé");
            return request.getSession();
        }else {
            log.info("Ancienne session récupéreé");
            return session;
        }
    }

    public Boolean isSessionOpen(HttpServletRequest request){
        if (request.getSession(false) == null) return false;
        return true;
    }

    public User getUserFromSession(HttpSession session) {
        var sessionUser = session.getAttribute("user");
        if(sessionUser == null) return null;
        else {
            return (User)sessionUser;
        }
    }

    public void saveLastPage(HttpSession session, String currentPage){
        session.setAttribute("currentLastPage", currentPage);
    }

    public String getLastPage(HttpSession session){
        var s = session.getAttribute("currentLastPage");
        if (s == null) return "index";
        else {
            log.info(s.toString());
            return s.toString();
        }
    }
}

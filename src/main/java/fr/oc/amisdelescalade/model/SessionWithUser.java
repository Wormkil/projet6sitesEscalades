package fr.oc.amisdelescalade.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpSession;

@ToString
@Setter
@Getter
public class SessionWithUser{
    public final User u;
    public final HttpSession s;

    public SessionWithUser(HttpSession s, User u) {
        this.u = u;
        this.s = s;
    }
}

/*package fr.oc.amisdelescalade.dao;

import fr.oc.amisdelescalade.beans.User;
import fr.oc.amisdelescalade.Projet6Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Controller
public class JdbcUserDAO {
    private final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void initTableUsers(){
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users");
        log.info("Destruction de la TABLE Users si elle existe");

        String sql =" CREATE TABLE Users(userId SERIAL, email VARCHAR(100), "+
                    " password VARCHAR(80), passwordConfirm VARCHAR(80), " +
                    " fullName VARCHAR(80), userName VARCHAR(80)," +
                    " country VARCHAR(80), creationAccount VARCHAR(20)," +
                    " officialMember BOOLEAN )";
        jdbcTemplate.execute(sql);
        log.info("Création de la TABLE Users");

        sql = "INSERT INTO Users(" +
                "   email,        password, passwordConfirm, fullName, userName, country, creationAccount, officialMember)" +
        " VALUES ('aze@aze.com', 'pass1pass1', 'pass1pass1', 'Amande', 'Armand','France', '1000-01-01', '1')";
        jdbcTemplate.batchUpdate(sql);
        log.info("Insertion d'un utilisateur : 'aze@aze.com', 'pass1pass1', 'pass1pass1', 'Amande', 'Armand','France','1000-01-01', '1'");
    }

    public User getById(long id){
        return new User();
    }

    public User getByEmail(String email){
        return new User();
    }

    public void setUser(User user) {

    }

    public void supprById(long id){

    }


}*/

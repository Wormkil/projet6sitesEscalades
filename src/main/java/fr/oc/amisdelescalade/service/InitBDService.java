package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.Bloc;
import fr.oc.amisdelescalade.model.Blocs;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.model.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class InitBDService {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ClimbSitesService climbSitesService;


    public void initBD() {
        fillSomeUsers();
        fillSomeClimbingSites();
    }

    public void fillSomeUsers(){
        User user = new User();

        user.setId(1);
        user.setOfficialMember("false");
        user.setCountry("France");
        user.setUserName("Aze");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("aze@aze.com");
        user.setFullName("A ze");
        log.info(user.toString());
        userService.saveUser(user);

        user.setId(2);
        user.setOfficialMember("false");
        user.setCountry("France");
        user.setUserName("Qsd");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("qsd@qsd.com");
        user.setFullName("Q sd");
        log.info(user.toString());
        userService.saveUser(user);

        user.setId(3);
        user.setOfficialMember("false");
        user.setCountry("France");
        user.setUserName("Wxc");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("wxc@wxc.com");
        user.setFullName("W xc");
        log.info(user.toString());
        userService.saveUser(user);
    }

    public void fillSomeClimbingSites() {
        ClimbSites cs = new ClimbSites();
        cs.setId(1);
        cs.setAccess("Il faut gagner la D13 qui parcours les hauts de St Leu. La ravine se trouve juste au nord du quartier “L’étang St Leu”, cf la carte. Aire de stationnement au niveau du panneau indiquant “Etang St Leu”.");
        cs.setBlocs_id(1);
        cs.setDescription("Un petit secteur avec quelques beaux passages. Idéal pour ceux qui habitent dans le coin ! Il n’y a pas de marche d’approche et vous pouvez y aller en famille. Des secteurs à l’ombre pour grimper tout au long de la journée.");
        cs.setName("RAVINE CLAUDE");
        cs.setSector("Charlie & les Claudettes");
        cs.setDifficulty("- 2 à 3 (orange) : 29 blocs\n" +
                "- 4 à 5a (bleu) : 34 blocs\n" +
                "- 5b à 6a (rouge) : 21 blocs\n" +
                "- 6b à 7a : 17 blocs\n" +
                "- 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("images/mapAcess/ravineClaudeAcess.png");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        Blocs bls = new Blocs();
        bls.setName("RAVINE CLAUDE");
        bls.setId(1);
        bls.setPathMapBlocs("images/mapBlocs/ravineClaudeBlocs.png");
        log.info(bls.toString());
        climbSitesService.saveBlocs(bls);

        Bloc bl = new Bloc();
        bl.setId(1);
        bl.setBlocs_id(1);
        bl.setNumber("1");
        bl.setQuotes("4b");
        bl.setNotes("@");
        log.info(bl.toString());
        climbSitesService.saveBl(bl);
        bl.setId(2);
        bl.setBlocs_id(1);
        bl.setNumber("2");
        bl.setQuotes("4b");
        bl.setNotes("");
        log.info(bl.toString());
        climbSitesService.saveBl(bl);
        bl.setId(3);
        bl.setBlocs_id(1);
        bl.setNumber("3");
        bl.setQuotes("5a");
        bl.setNotes("");
        log.info(bl.toString());
        climbSitesService.saveBl(bl);
    }
}

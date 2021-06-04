package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.Projet6Application;
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
    @Autowired
    private BlocsService blocsService;
    @Autowired
    private BlocService blocService;


    public void initBD() {
        fillSomeUsers();
        fillSomeClimbingSites();
        //fillSomeGhotClimbingSites();
    }

    private void fillSomeUsers(){
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

    private void fillSomeClimbingSites() {
        ClimbSites cs = new ClimbSites();
        cs.setId(1);
        cs.setName("Connelles");
        cs.setDescription("Site de voies d'une longueur intéressante pour ses voies jusqu'au 5c, du 6a au 6c et du 7a au 7c.");
        cs.setAccess("5 min en montée facile depuis la Rue des Falaises où vous trouverez un parking");
        cs.setRegion("Normandie");
        cs.setCountry("France");
        cs.setBestSeason("Automne / Hivers / Printemps");
        cs.setOrientations("W");
        cs.setCotationsRange("du 3c au 7c");
        cs.setNbRoute("+300");
        cs.setEquipment("Engagé");
        cs.setMaxHeight("35m");
        cs.setStoneType("Craie");
        cs.setProfile("Dévers, Vertical, Dalle");
        cs.setPlugType("Trous, Réglettes, Aplats");
        cs.setInfoSup("Un utilisateur a rapporté :\"Suite à un éboulement en avril 2013, les secteurs 4 et 5 sont interdit d'accès jusqu'à nouvel ordre.\"");
        cs.setPathImages("../images/Connelles/");
        cs.setBlocsId(1L);
        log.info(cs.toString());
        climbSitesService.saveCS(cs);


       /* ClimbSites cs = new ClimbSites();
        cs.setId(1);
        cs.setAccess("Il faut gagner la D13 qui parcours les hauts de St Leu. La ravine se trouve juste au nord du quartier “L’étang St Leu”, cf la carte. Aire de stationnement au niveau du panneau indiquant “Etang St Leu”.");
        cs.setBlocsId(1);
        cs.setDescription("Un petit secteur avec quelques beaux passages. Idéal pour ceux qui habitent dans le coin ! Il n’y a pas de marche d’approche et vous pouvez y aller en famille. Des secteurs à l’ombre pour grimper tout au long de la journée.");
        cs.setName("RAVINE CLAUDE");
        cs.setSector("Charlie & les Claudettes");
        cs.setDifficulty(" 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/ravineClaudeAcess.png");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        Blocs bls = new Blocs();
        bls.setName("RAVINE CLAUDE");
        bls.setId(1);
        bls.setPathMapBlocs("../images/mapBlocs/claudeRavineBlocs.png");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);

        Bloc bl = new Bloc();
        bl.setId(1);
        bl.setBlocsId(1);
        bl.setNumber("1");
        bl.setQuotes("4b");
        bl.setNotes("@");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(2);
        bl.setBlocsId(1);
        bl.setNumber("2");
        bl.setQuotes("4b");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(3);
        bl.setBlocsId(1);
        bl.setNumber("3");
        bl.setQuotes("5a");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(4);
        bl.setBlocsId(1);
        bl.setNumber("4");
        bl.setQuotes("7b");
        bl.setNotes("La flemme de l'énergumène");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(5);
        bl.setBlocsId(1);
        bl.setNumber("5");
        bl.setQuotes("6a");
        bl.setNotes("Talon sous les Chtis");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(6);
        bl.setBlocsId(1);
        bl.setNumber("6");
        bl.setQuotes("4b");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(7);
        bl.setBlocsId(1);
        bl.setNumber("6 bis");
        bl.setQuotes("5b");
        bl.setNotes("pied imposé");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(8);
        bl.setBlocsId(1);
        bl.setNumber("6 ter");
        bl.setQuotes("3a");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(9);
        bl.setBlocsId(1);
        bl.setNumber("7");
        bl.setQuotes("3b");
        bl.setNotes("@");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(10);
        bl.setBlocsId(1);
        bl.setNumber("8");
        bl.setQuotes("3b");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);

        *//*-----------------------------------------------------------*//*

        cs.setId(2);
        cs.setAccess("""
                Depuis le route des Tamarins prenez la sortie “Plateau Caillou”. Après la sortie restez sur la D6 et prenez la déviation de Plateau Caillou. Au rond point allez tout droit en direction de St Gilles les hauts, puis prenez 500 m plus loin la D8 sur votre gauche direction “Bellemène”. Montez sur 900 m et prenez à gauche la D4E, “Bellemène”. Dès que vous aurez passé la ravine Bernica (la première traversée), prenez la route qui descend sur votre gauche (direction “apiculteur”) et garez vous 500 m plus bas, juste avant une nouvelle intersection menant à un apiculteur.
                Trouvez le marquage 50 m en amont (rubalis et / ou peinture) et suivez le jusqu’au bloc ...
                """);
        cs.setBlocsId(2);
        cs.setDescription("""
                La ravine Bernica recèle de nombreux passages dans tout les niveaux, plutôt dans le 5 et le 6 pour le moment. Attention toutefois les réceptions sont souvent chaotiques et nécessitent un bon agencement de crashpad ainsi que de bons 
                L’accès peut être assez difficile à trouver dans les premiers mètres tant de nombreuses sentes parcours le plateau bordant la ravine. Il n’est pas facile d’accès pour des enfants (main courante et désescalade).
                """);
        cs.setName("RAVINE BERNICA");
        cs.setSector("Miguel Ange");
        cs.setDifficulty(" Default : 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/ravineBernicaAccess.jpg");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        bls.setName("RAVINE BERNICA - MIGUEL ANGE");
        bls.setId(2);
        bls.setPathMapBlocs("../images/mapBlocs/ravineBernicaBlocsMiguelAnge.jpg");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);

        bl.setId(10);
        bl.setBlocsId(2);
        bl.setNumber("1");
        bl.setQuotes("");
        bl.setNotes("Projet");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(11);
        bl.setBlocsId(2);
        bl.setNumber("2");
        bl.setQuotes("4");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(12);
        bl.setBlocsId(2);
        bl.setNumber("3");
        bl.setQuotes("");
        bl.setNotes("Projet @");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(13);
        bl.setBlocsId(2);
        bl.setNumber("4");
        bl.setQuotes("5b");
        bl.setNotes("@");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(14);
        bl.setBlocsId(2);
        bl.setNumber("5");
        bl.setQuotes("5c");
        bl.setNotes("Sans l'arête de droite");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(15);
        bl.setBlocsId(2);
        bl.setNumber("6");
        bl.setQuotes("");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(16);
        bl.setBlocsId(2);
        bl.setNumber("7");
        bl.setQuotes("4");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(17);
        bl.setBlocsId(2);
        bl.setNumber("8");
        bl.setQuotes("");
        bl.setNotes("Projet");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(18);
        bl.setBlocsId(2);
        bl.setNumber("9");
        bl.setQuotes("5a");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(19);
        bl.setBlocsId(2);
        bl.setNumber("10");
        bl.setQuotes("5b");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);

        *//*-----------------------------------------------------------*//*

        cs.setId(3);
        cs.setAccess("""
                Depuis l'entrée nord de Saint Leu. Empruntez le chemin surprise. Montez les lacets et tournez à la deuxième à droite, chemin batterie. Descendez jusqu'au grand parking devant les canons.
                Du parking (10min): Empruntez le sentier pavé qui monte. A la patte d'oie, prenez la sente entre les branches sur la droite. Marchez dans la fôret puis descendre dans un lit de ravine étroit. Le secteur poudding est à votre gauche.
                """);
        cs.setBlocsId(3);
        cs.setDescription("""
                Le site propose quelques blocs interressants malgré un rocher agressif. La roche paté, véritable promontoire visible de Saint Leu permet de grimper avec l'océan en toile de fond. Le bastion se dresse tel une pyramide au milieu de nulle part...
                """);
        cs.setName("ROCHE PÂTÉ");
        cs.setSector("");
        cs.setDifficulty(" Default : 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/rochePateAccess.jpg");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        bls.setName("ROCHE PÂTÉ");
        bls.setId(3);
        bls.setPathMapBlocs("../images/mapBlocs/rochePateBlocs.jpg");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);

        bl.setId(20);
        bl.setBlocsId(3);
        bl.setNumber("1");
        bl.setQuotes("");
        bl.setNotes("@");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(21);
        bl.setBlocsId(3);
        bl.setNumber("2");
        bl.setQuotes("");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(22);
        bl.setBlocsId(3);
        bl.setNumber("3");
        bl.setQuotes("");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(23);
        bl.setBlocsId(3);
        bl.setNumber("4");
        bl.setQuotes("");
        bl.setNotes("@");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(24);
        bl.setBlocsId(3);
        bl.setNumber("5");
        bl.setQuotes("");
        bl.setNotes("@");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(25);
        bl.setBlocsId(3);
        bl.setNumber("6");
        bl.setQuotes("");
        bl.setNotes("@");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(26);
        bl.setBlocsId(3);
        bl.setNumber("7");
        bl.setQuotes("");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(27);
        bl.setBlocsId(3);
        bl.setNumber("8");
        bl.setQuotes("");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(28);
        bl.setBlocsId(3);
        bl.setNumber("9");
        bl.setQuotes("");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);
        bl.setId(29);
        bl.setBlocsId(3);
        bl.setNumber("10");
        bl.setQuotes("");
        bl.setNotes("");
        log.info(bl.toString());
        blocService.saveBl(bl);*/
    }

    private void fillSomeGhotClimbingSites() {

       /* ClimbSites cs = new ClimbSites();
        cs.setId(4);
        cs.setAccess("Il faut gagner la D13 qui parcours les hauts de St Leu. La ravine se trouve juste au nord du quartier “L’étang St Leu”, cf la carte. Aire de stationnement au niveau du panneau indiquant “Etang St Leu”.");
        cs.setBlocsId(1);
        cs.setDescription("Un petit secteur avec quelques beaux passages. Idéal pour ceux qui habitent dans le coin ! Il n’y a pas de marche d’approche et vous pouvez y aller en famille. Des secteurs à l’ombre pour grimper tout au long de la journée.");
        cs.setName("RAVINE CLAUDE2");
        cs.setSector("Charlie & les Claudettes2");
        cs.setDifficulty(" 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/ravineClaudeAcess.png");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        Blocs bls = new Blocs();
        bls.setName("RAVINE CLAUDE2");
        bls.setId(4);
        bls.setPathMapBlocs("../images/mapBlocs/claudeRavineBlocs.png");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);

        *//*-----------------------------------------------------------*//*

        cs.setId(5);
        cs.setAccess("""
                Depuis le route des Tamarins prenez la sortie “Plateau Caillou”. Après la sortie restez sur la D6 et prenez la déviation de Plateau Caillou. Au rond point allez tout droit en direction de St Gilles les hauts, puis prenez 500 m plus loin la D8 sur votre gauche direction “Bellemène”. Montez sur 900 m et prenez à gauche la D4E, “Bellemène”. Dès que vous aurez passé la ravine Bernica (la première traversée), prenez la route qui descend sur votre gauche (direction “apiculteur”) et garez vous 500 m plus bas, juste avant une nouvelle intersection menant à un apiculteur.
                Trouvez le marquage 50 m en amont (rubalis et / ou peinture) et suivez le jusqu’au bloc ...
                """);
        cs.setBlocsId(2);
        cs.setDescription("""
                La ravine Bernica recèle de nombreux passages dans tout les niveaux, plutôt dans le 5 et le 6 pour le moment. Attention toutefois les réceptions sont souvent chaotiques et nécessitent un bon agencement de crashpad ainsi que de bons 
                L’accès peut être assez difficile à trouver dans les premiers mètres tant de nombreuses sentes parcours le plateau bordant la ravine. Il n’est pas facile d’accès pour des enfants (main courante et désescalade).
                """);
        cs.setName("RAVINE BERNICA2");
        cs.setSector("Miguel Ange2");
        cs.setDifficulty(" Default : 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/ravineBernicaAccess.jpg");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        bls.setName("RAVINE BERNICA - MIGUEL ANGE2");
        bls.setId(4);
        bls.setPathMapBlocs("../images/mapBlocs/ravineBernicaBlocsMiguelAnge.jpg");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);


        *//*-----------------------------------------------------------*//*

        cs.setId(6);
        cs.setAccess("""
                Depuis l'entrée nord de Saint Leu. Empruntez le chemin surprise. Montez les lacets et tournez à la deuxième à droite, chemin batterie. Descendez jusqu'au grand parking devant les canons.
                Du parking (10min): Empruntez le sentier pavé qui monte. A la patte d'oie, prenez la sente entre les branches sur la droite. Marchez dans la fôret puis descendre dans un lit de ravine étroit. Le secteur poudding est à votre gauche.
                """);
        cs.setBlocsId(3);
        cs.setDescription("""
                Le site propose quelques blocs interressants malgré un rocher agressif. La roche paté, véritable promontoire visible de Saint Leu permet de grimper avec l'océan en toile de fond. Le bastion se dresse tel une pyramide au milieu de nulle part...
                """);
        cs.setName("ROCHE PÂTÉ2");
        cs.setSector("2");
        cs.setDifficulty(" Default : 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/rochePateAccess.jpg");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        bls.setName("ROCHE PÂTÉ2");
        bls.setId(6);
        bls.setPathMapBlocs("../images/mapBlocs/rochePateBlocs.jpg");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);

        *//*-----------------------------------------------------------*//*

        cs.setId(7);
        cs.setAccess("Il faut gagner la D13 qui parcours les hauts de St Leu. La ravine se trouve juste au nord du quartier “L’étang St Leu”, cf la carte. Aire de stationnement au niveau du panneau indiquant “Etang St Leu”.");
        cs.setBlocsId(1);
        cs.setDescription("Un petit secteur avec quelques beaux passages. Idéal pour ceux qui habitent dans le coin ! Il n’y a pas de marche d’approche et vous pouvez y aller en famille. Des secteurs à l’ombre pour grimper tout au long de la journée.");
        cs.setName("RAVINE CLAUDE3");
        cs.setSector("Charlie & les Claudettes3");
        cs.setDifficulty(" 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/ravineClaudeAcess.png");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        bls.setName("RAVINE CLAUDE3");
        bls.setId(7);
        bls.setPathMapBlocs("../images/mapBlocs/claudeRavineBlocs.png");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);

        *//*-----------------------------------------------------------*//*

        cs.setId(8);
        cs.setAccess("""
                Depuis le route des Tamarins prenez la sortie “Plateau Caillou”. Après la sortie restez sur la D6 et prenez la déviation de Plateau Caillou. Au rond point allez tout droit en direction de St Gilles les hauts, puis prenez 500 m plus loin la D8 sur votre gauche direction “Bellemène”. Montez sur 900 m et prenez à gauche la D4E, “Bellemène”. Dès que vous aurez passé la ravine Bernica (la première traversée), prenez la route qui descend sur votre gauche (direction “apiculteur”) et garez vous 500 m plus bas, juste avant une nouvelle intersection menant à un apiculteur.
                Trouvez le marquage 50 m en amont (rubalis et / ou peinture) et suivez le jusqu’au bloc ...
                """);
        cs.setBlocsId(2);
        cs.setDescription("""
                La ravine Bernica recèle de nombreux passages dans tout les niveaux, plutôt dans le 5 et le 6 pour le moment. Attention toutefois les réceptions sont souvent chaotiques et nécessitent un bon agencement de crashpad ainsi que de bons 
                L’accès peut être assez difficile à trouver dans les premiers mètres tant de nombreuses sentes parcours le plateau bordant la ravine. Il n’est pas facile d’accès pour des enfants (main courante et désescalade).
                """);
        cs.setName("RAVINE BERNICA3");
        cs.setSector("Miguel Ange3");
        cs.setDifficulty(" Default : 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/ravineBernicaAccess.jpg");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        bls.setName("RAVINE BERNICA - MIGUEL ANGE3");
        bls.setId(8);
        bls.setPathMapBlocs("../images/mapBlocs/ravineBernicaBlocsMiguelAnge.jpg");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);


        *//*-----------------------------------------------------------*//*

        cs.setId(9);
        cs.setAccess("""
                Depuis l'entrée nord de Saint Leu. Empruntez le chemin surprise. Montez les lacets et tournez à la deuxième à droite, chemin batterie. Descendez jusqu'au grand parking devant les canons.
                Du parking (10min): Empruntez le sentier pavé qui monte. A la patte d'oie, prenez la sente entre les branches sur la droite. Marchez dans la fôret puis descendre dans un lit de ravine étroit. Le secteur poudding est à votre gauche.
                """);
        cs.setBlocsId(3);
        cs.setDescription("""
                Le site propose quelques blocs interressants malgré un rocher agressif. La roche paté, véritable promontoire visible de Saint Leu permet de grimper avec l'océan en toile de fond. Le bastion se dresse tel une pyramide au milieu de nulle part...
                """);
        cs.setName("ROCHE PÂTÉ3");
        cs.setSector("3");
        cs.setDifficulty(" Default : 2 à 3 (orange) : 29 blocs / 4 à 5a (bleu) : 34 blocs / 5b à 6a (rouge) : 21 blocs / 6b à 7a : 17 blocs / 7b et plus (blanc) : 3 blocs");
        cs.setPathMapAcess("../images/mapAcess/rochePateAccess.jpg");
        cs.setRegion("La Réunion");
        log.info(cs.toString());
        climbSitesService.saveCS(cs);

        bls.setName("ROCHE PÂTÉ3");
        bls.setId(9);
        bls.setPathMapBlocs("../images/mapBlocs/rochePateBlocs.jpg");
        log.info(bls.toString());
        blocsService.saveBlocs(bls);*/


    }
}

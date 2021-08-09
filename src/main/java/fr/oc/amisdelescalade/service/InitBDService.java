package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.Projet6Application;
import fr.oc.amisdelescalade.model.ClimbSites;
import fr.oc.amisdelescalade.model.Comment;
import fr.oc.amisdelescalade.model.Topo;
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
    private BlocService blocService;
    @Autowired
    private CommentService comService;
    @Autowired
    private TopoService topoService;


    public void initBD() {
        fillSomeUsers();
        fillSomeClimbingSites();
        fillSomeComment();
        fillSomeTopo();
        log.info("---------- Data_base filled -----------------");
    }

    private void fillSomeUsers() {
        User user = new User();

        user.setId(1);
        user.setCountry("France");
        user.setUserName("Aze");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("aze@aze.com");
        user.setFullName("A ze");
        user.setOfficialMember("true");
        userService.saveUser(user);

        user.setId(2);
        user.setCountry("France");
        user.setUserName("Qsd");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("qsd@qsd.com");
        user.setFullName("Q sd");
        user.setOfficialMember("false");
        userService.saveUser(user);

        user.setId(3);
        user.setCountry("France");
        user.setUserName("Wxc");
        user.setCreationAccount("2021-05-29");
        user.setPasswordConfirm("123456789");
        user.setPassword("123456789");
        user.setEmail("wxc@wxc.com");
        user.setOfficialMember("false");
        user.setFullName("W xc");
        userService.saveUser(user);
    }

    private void fillSomeComment() {
        Comment com = new Comment();

        com.setId(1L);
        com.setUserId(1L);
        com.setCsId(1L);
        com.setContent("J'aime manger des bananes avant de grimper sur ces blocs.");
        com.setCreationDate("2021-05-29 12:30");
        comService.saveCom(com);

        com.setId(14L);
        com.setUserId(2L);
        com.setCsId(1L);
        com.setContent("Moi aussi j'aime manger des bananes avant d'escalader un bloc.");
        com.setCreationDate("2021-05-29 16:30");
        comService.saveCom(com);

        com.setId(3L);
        com.setUserId(3L);
        com.setCsId(1L);
        com.setContent("Je déteste manger des bananes !");
        com.setCreationDate("2021-05-29 18:59");
        comService.saveCom(com);

        com.setId(4L);
        com.setUserId(1L);
        com.setCsId(2L);
        com.setContent("J'aime manger des kiwis avant de grimper sur ces blocs.");
        com.setCreationDate("2021-05-29 12:30");
        comService.saveCom(com);

        com.setId(5L);
        com.setUserId(2L);
        com.setCsId(2L);
        com.setContent("Moi aussi j'aime manger des kiwis avant d'escalader un bloc.");
        com.setCreationDate("2021-05-29 16:30");
        comService.saveCom(com);

        com.setId(6L);
        com.setUserId(3L);
        com.setCsId(2L);
        com.setContent("Je déteste manger des kiwis !");
        com.setCreationDate("2021-05-29 18:59");
        comService.saveCom(com);

        com.setId(7L);
        com.setUserId(1L);
        com.setCsId(3L);
        com.setContent("J'aime manger des cerises avant de grimper sur ces blocs.");
        com.setCreationDate("2021-05-29 12:30");
        comService.saveCom(com);

        com.setId(8L);
        com.setUserId(2L);
        com.setCsId(3L);
        com.setContent("Moi aussi j'aime manger des cerises avant d'escalader un bloc.");
        com.setCreationDate("2021-05-29 16:30");
        comService.saveCom(com);

        com.setId(9L);
        com.setUserId(3L);
        com.setCsId(3L);
        com.setContent("Je déteste manger des cerises !");
        com.setCreationDate("2021-05-29 18:59");
        comService.saveCom(com);

        com.setId(10L);
        com.setUserId(3L);
        com.setCsId(1L);
        com.setContent("Hum les bananes c'est délicieux !");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);

        com.setId(11L);
        com.setUserId(1L);
        com.setCsId(1L);
        com.setContent("Les cerises aussi !");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);

        com.setId(12L);
        com.setUserId(2L);
        com.setCsId(1L);
        com.setContent("Les cerises mures oui, sinon non.");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);

        com.setId(13L);
        com.setUserId(3L);
        com.setCsId(1L);
        com.setContent("Viva les tarte au framboises");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);

        com.setId(2L);
        com.setUserId(3L);
        com.setCsId(1L);
        com.setContent("Je voudrais être un commentaires très très long pour voir comment ce comporte le site, je ne sais pas encore combien de caractère max je vais autoriser. Peut être comme tweeter et ses *jsaisppluscombien* mais surement plus car je ne veux pas que les utlisateurs ne puisse que faire un tweet comme commentaire. Pourquoi pas 600 ? J'ai pris ce nombre au hasard pake en vrai je ne sais absoluement pas combien je devrais mettre. Déja une phrase de 600 caractère y'a de la place pour s'exprimer d'ailleurs ce message fait jsutement bientôt six-cent caractère, a peu près maintenant");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);

        com.setId(15L);
        com.setUserId(1L);
        com.setCsId(1L);
        com.setContent("Vive l'escalades et vive la vie !");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);

        com.setId(16L);
        com.setUserId(2L);
        com.setCsId(1L);
        com.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);

        com.setId(17L);
        com.setUserId(3L);
        com.setCsId(1L);
        com.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eget mi proin sed libero enim sed faucibus turpis in. Augue neque gravida in fermentum et sollicitudin ac. Ultrices mi tempus imperdiet nulla malesuada pellentesque elit eget. Dictum sit amet justo donec enim. Vitae ultricies leo integer malesuada nunc vel risus. In metus vulputate eu scelerisque felis imperdiet proin. Iaculis at erat pellentesque adipiscing commodo elit at imperdiet. Placerat duis ultricies lacus sed turpis. Ut porttitor leo a diam sollicitudin tempor id eu nisl. Posuere sollicitudin aliquam ultrices sagittis orci a scelerisque purus semper.");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);

        com.setId(18L);
        com.setUserId(2L);
        com.setCsId(1L);
        com.setContent("Je suis le onzième commentaire qui ne doit pas être afficher !");
        com.setCreationDate("2021-05-29 19:59");
        comService.saveCom(com);
    }

    private void fillSomeTopo() {
        Topo topo = new Topo();

        topo.setId(1);
        topo.setName("La courgette");
        topo.setDescription("Je suis la description de la courgette, je suis verte, longue ou rounde et plein de nutriments ! Vive les courgette ! Farcies, tarte ou à la poele il y'en en à pour tous les goûts");
        topo.setLocalisation("Haute-Normandie");
        topo.setAuthorId("1");
        topo.setReleaseDate("24-06-2021");
        topo.setAvailable("true");
        topo.setBeingReserved("false");
        topo.setReserved("false");
        topo.setOwnerId("1");
        topoService.saveTopo(topo);

        topo.setId(2);
        topo.setName("La banna");
        topo.setDescription("Je suis la description de la bannane, je suis jaune, souvent longue et plein de nutriments ! Vive les Bananne ! Farcies, tarte ou à la poele il y'en en à pour tous les goûts");
        topo.setLocalisation("Limousins");
        topo.setAuthorId("1");
        topo.setReleaseDate("24-06-2021");
        topo.setAvailable("false");
        topo.setBeingReserved("true");
        topoService.saveTopo(topo);

        topo.setId(3);
        topo.setName("La tomate");
        topo.setDescription("Je suis la description de la tomate, je suis rouge, longue ou rounde et plein de nutriments ! Vive les tomate ! Farcies, tarte ou à la poele il y'en en à pour tous les goûts");
        topo.setLocalisation("Seine et marne");
        topo.setAuthorId("1");
        topo.setReleaseDate("24-06-2021");
        topo.setReserved("true");
        topoService.saveTopo(topo);

        topo.setId(4);
        topo.setName("La cerise");
        topo.setDescription("Je suis la description de la cerise, je suis rouge, longue ou rounde et plein de nutriments ! Vive les tomate ! Farcies, tarte ou à la poele il y'en en à pour tous les goûts");
        topo.setLocalisation("Isère");
        topo.setAuthorId("1");
        topo.setReleaseDate("24-06-2021");
        topo.setAvailable("true");
        topo.setBeingReserved("false");
        topo.setReserved("false");
        topoService.saveTopo(topo);

        topo.setId(5);
        topo.setName("La courge");
        topo.setDescription("Je suis la description de la courgette, je suis verte, longue ou rounde et plein de nutriments ! Vive les courgette ! Farcies, tarte ou à la poele il y'en en à pour tous les goûts");
        topo.setLocalisation("Haute-Normandie");
        topo.setAuthorId("1");
        topo.setReleaseDate("24-06-2021");
        topo.setAvailable("true");
        topo.setBeingReserved("false");
        topoService.saveTopo(topo);


        var cpt = 6;
        while (cpt < 64) {
            topo.setName(cpt+"");
            topo.setId(cpt);
            topoService.saveTopo(topo);
            cpt++;
        }

        topo.setId(64);
        topo.setName("L'avant dernier topo");
        topo.setDescription("Je suis la description de la courgette, je suis verte, longue ou rounde et plein de nutriments ! Vive les courgette ! Farcies, tarte ou à la poele il y'en en à pour tous les goûts");
        topo.setLocalisation("Haute-Normandie");
        topo.setAuthorId("1");
        topo.setReleaseDate("24-06-2021");
        topo.setAvailable("true");
        topo.setBeingReserved("false");
        topoService.saveTopo(topo);

        topo.setId(65);
        topo.setName("Le dernier topo");
        topo.setDescription("Je suis la description de la courgette, je suis verte, longue ou rounde et plein de nutriments ! Vive les courgette ! Farcies, tarte ou à la poele il y'en en à pour tous les goûts");
        topo.setLocalisation("Haute-Normandie");
        topo.setAuthorId("1");
        topo.setReleaseDate("24-06-2021");
        topo.setAvailable("true");
        topo.setBeingReserved("false");
        topoService.saveTopo(topo);

    }

    private void fillSomeClimbingSites() {
        ClimbSites cs = new ClimbSites();
        cs.setId(1);
        cs.setName("Connelles");
        cs.setDescription("Site de voies d'une longueur intéressante pour ses voies jusqu'au 5c, du 6a au 6c et du 7a au 7c.");
        cs.setAccess("5 min en montée facile depuis la Rue des Falaises où vous trouverez un parking");
        cs.setRegion("Normandie");
        cs.setCountry("France");
        cs.setBestSeason("Automne, Hivers, Printemps");
        cs.setOrientations("O");
        cs.setCotationsRange("du 3c au 7c");
        cs.setNbRoute("+300");
        cs.setEquipment("Engagé");
        cs.setMaxHeight("35m");
        cs.setStoneType("Craie");
        cs.setProfile("Dévers, Vertical, Dalle");
        cs.setPlugType("Trous, Réglettes, Aplats");
        cs.setInfoSup("Un utilisateur a rapporté :\"Suite à un éboulement en avril 2013, les secteurs 4 et 5 sont interdit d'accès jusqu'à nouvel ordre.\"");
        cs.setPathImages("../images/Connelles/");
        cs.setUrlggmaps("https://www.google.com/maps/embed?pb=!1m21!1m12!1m3!1d1251.3133625089833!2d1.2708611700125734!3d49.27635533304012!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m6!3e2!4m0!4m3!3m2!1d49.2763497!2d1.2717794999999998!5e1!3m2!1sen!2sfr!4v1622822413165!5m2!1sen!2sfr");
        cs.setOfficial("true");
        cs.setAuthorId("1");
        climbSitesService.saveCS(cs);

        cs.setId(2);
        cs.setName("Falaise de Leschaux (Orelle)");
        cs.setDescription("Site de voies d'une longueur intéressant pour ses voies jusqu'au 5c, du 6a au 6c et du 7a au 7c.");
        cs.setAccess("Entre 25 et 30 minute en montant depuis le parking. La petite randonné est belle et sympathique");
        cs.setRegion("Auvergne-Rhône-Alpes");
        cs.setCountry("France");
        cs.setBestSeason("Automne, Printemps");
        cs.setOrientations("S");
        cs.setCotationsRange("du 3b au 7b");
        cs.setNbRoute("+10");
        cs.setEquipment("Terrain d'aventure, Sportif");
        cs.setMaxHeight("25m");
        cs.setStoneType("Calcaire");
        cs.setProfile("Dévers, Dalle");
        cs.setPlugType("Réglettes");
        cs.setInfoSup("Sites internet : http://Https://www.orelle.net/fiche-escalade-627635 ou https://static.apidae-tourisme.com/filestore/objets-touristiques/documents/89/68/1721433.pdf");
        cs.setPathImages("../images/Orelle/");
        cs.setUrlggmaps("https://www.google.com/maps/embed?pb=!1m21!1m12!1m3!1d2928.5113945590356!2d6.570911052309172!3d45.21025449183142!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m6!3e2!4m0!4m3!3m2!1d45.210454899999995!2d6.5726641!5e1!3m2!1sen!2sfr!4v1623059645192!5m2!1sen!2sfr");
        cs.setOfficial("true");
        cs.setAuthorId("2");
        climbSitesService.saveCS(cs);

        cs.setId(3);
        cs.setName("Falaise du Coteau (Thoré la Rochette)");
        cs.setDescription("Site de voies d'une longueur intéressant pour ses voies jusqu'au 5c et du 6a au 6c .");
        cs.setAccess("EA 9 km à l'ouest de Vendôme, près de la gare du train touristique de la vallée du Loir (Maison du vin). De Vendôme prendre direction Montoire (D917) sur 6 km puis à droite la D62 (nord, la 3ème route à droite après le passage à niveau) vers Thoré-la-Rochette. Le site est indiqué 500 m à droite vers un chemin de terre longeant la voie ferrée.");
        cs.setRegion("Centre-Val de Loire");
        cs.setCountry("France");
        cs.setBestSeason("Automne, Été, Printemps");
        cs.setOrientations("O");
        cs.setCotationsRange("du 3a au 6c");
        cs.setNbRoute("+100");
        cs.setEquipment("Sportif");
        cs.setMaxHeight("7m");
        cs.setStoneType("Calcaire");
        cs.setProfile("Vertical, Dalle");
        cs.setPlugType("Réglettes, Plats");
        cs.setInfoSup("Site(s) internet : http://www.ffme.fr/site/falaise-fiche/313.html");
        cs.setPathImages("../images/Thore/");
        cs.setUrlggmaps("https://www.google.com/maps/embed?pb=!1m21!1m12!1m3!1d6133.491013215771!2d0.9598856298332028!3d47.77506548130712!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m6!3e2!4m0!4m3!3m2!1d47.7757444!2d0.9632643999999999!5e1!3m2!1sen!2sfr!4v1623060010818!5m2!1sen!2sfr");
        cs.setOfficial("false");
        cs.setAuthorId("3");
        climbSitesService.saveCS(cs);

        var cpt = 4;
        while (cpt < 50) {
            cs.setId(cpt);
            cs.setName(String.valueOf(cpt));
            cs.setAuthorId("1");
            climbSitesService.saveCS(cs);
            cpt++;
        }
        cpt--;
        log.info("cpt = "+cpt);
    }
}
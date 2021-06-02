package fr.oc.amisdelescalade;

import fr.oc.amisdelescalade.service.InitBDService;
import fr.oc.amisdelescalade.utils.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Projet6Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

	public static SessionManager sessionManager = new SessionManager();

	@Autowired
	private InitBDService idbs;

	public static void main(String[] args) {
		log.info("Lancement du main de Projet6application");
		SpringApplication.run(Projet6Application.class, args);

	}


	@Override
	public void run(String...args) {

		log.info("Lancement de Projet6application.run");
		idbs.initBD();

		/*jdbcTemplate.execute("DROP TABLE IF EXISTS Users");
		log.info("Destruction de la TABLE Users si elle existe");

		String sql = " CREATE TABLE Users(userId SERIAL, email VARCHAR(100), "+
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
		log.info("Insertion d'un utilisateur : 'aze@aze.com', 'pass1pass1', 'pass1pass1', 'Amande', 'Armand','France','1000-01-01', '1'");*/
		/*UserManager userManager = new UserManager();
		userManager.initTableUsers();*/
	}


}

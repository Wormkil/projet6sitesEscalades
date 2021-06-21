package fr.oc.amisdelescalade;

import fr.oc.amisdelescalade.service.InitBDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Projet6Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);

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
	}


}

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
		SpringApplication.run(Projet6Application.class, args);
	}

	@Override
	public void run(String...args) {
		idbs.initBD();
		log.info("Vous pouvez intéragir avec le site");
	}


}

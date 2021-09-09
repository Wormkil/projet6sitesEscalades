package fr.oc.amisdelescalade.service;


import fr.oc.amisdelescalade.Projet6Application;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Data
@Service
public class SendMailService {

    private static final Logger log = LoggerFactory.getLogger(Projet6Application.class);
    private static final String websiteMail = "amisdelescalade.officiel@gmail.com";

    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingAcceptanceMail(String ownerMail, String askerMail, String topoName) throws MessagingException {
        String from = websiteMail;
        String to = askerMail;

        MimeMessage message = mailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlMsg = "<h3><b>Bravo !</b></h3>" +
                "<p>Votre demande de réservation à bien été accépté ! Nous avons envoyé votre email au possesseur du topo pour qu'il puisse vous contacter.</p>" +
                "<p>Voici son mail si vous ne voulez pas attendre : </p>" +
                "<h3>"+ownerMail+"</h3>"+
                "<p>Merci d'avoir utilisé nos services, en espérant que vous apprécierez ce topo :)</p>"+
                "<p>Cordialement, les amis de l'escalades.</p>";
        message.setContent(htmlMsg, "text/html");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("Réservation du topo : '"+topoName+"' accépté");

        mailSender.send(message);
    }

    public void sendCoordonnateOfToposBookingAsker(String ownerMail, String askerMail, String topoName) throws MessagingException {
        String from = websiteMail;
        String to = ownerMail;

        MimeMessage message = mailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlMsg =
                "<p>Vous avez accepté la demande de réservation de votre topo : '"+topoName+"</p>" +
                "<p>Voici le mail du demandeur de la réservation : </p>" +
                "<h3>"+askerMail+"</h3>"+
                "<p>Contactez le afin de vous organisez pour le topo :)</p>"+
                "<p>Cordialement, les amis de l'escalades.</p>";
        message.setContent(htmlMsg, "text/html");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("Réservation du topo : '"+topoName+"' accépté");

        mailSender.send(message);
    }
}

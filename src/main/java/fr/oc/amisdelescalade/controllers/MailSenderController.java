package fr.oc.amisdelescalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class MailSenderController {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(){
        String from = "amisdelescalade.officiel@gmail.com";
        String to = "jouen.thibaut@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("This is a plain text email");
        message.setText("Hello guys! This is a plain text email.");

        mailSender.send(message);
    }
}

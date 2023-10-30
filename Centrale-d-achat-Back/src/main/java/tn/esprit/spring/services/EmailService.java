package tn.esprit.spring.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tn.esprit.spring.DTO.CheckoutDTO;
import tn.esprit.spring.interfaces.EmailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;

@Service
public class EmailService implements EmailSender {
    @Autowired
    private JavaMailSender mailSender;



    private JavaMailSender emailSender;
    private TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void send(CheckoutDTO checkoutDTO) {

    }

    @SneakyThrows
    @Override
    @Async
    public void send(String to, String email) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(email, true);
        helper.setTo(to);
        helper.setSubject("Évaluation et avis");
        helper.setFrom("charlesnicolehopital@gmail.com");
        mailSender.send(mimeMessage);
    }


    public void sendOfferEmail(String recipientEmail, String offerDescription, Integer etatOffre, LocalDate dateOffre, Integer offreId) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipientEmail);
        helper.setSubject("New Offer Available");

        Context context = new Context();
        context.setVariable("offerDescription", offerDescription);
        context.setVariable("etatOffre", etatOffre);
        context.setVariable("dateOffre",dateOffre);
        context.setVariable("offreId",offreId);


        String htmlContent = templateEngine.process("offer-template", context);
        String processedHtmlContent = templateEngine.process("offer-template", context);
        helper.setText(processedHtmlContent, true);

        emailSender.send(message);
    }
}

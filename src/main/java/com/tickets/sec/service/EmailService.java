package com.tickets.sec.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String otp) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Código de Verificación TicketPlay");
            helper.setText(buildEmailBody(otp), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error al enviar correo electrónico: " + e.getMessage());
        }
    }

    private String buildEmailBody(String otp) {
        return "<h1>Tu código de verificación para acceder a TicketPlay </h1>"
                + "<p>Tu código OTP es: <strong>" + otp + "</strong></p>"
                + "<p>Este código expirará en 5 minutos.</p>";
    }
}

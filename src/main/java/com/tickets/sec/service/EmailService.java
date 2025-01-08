package com.tickets.sec.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String otp) {

        try {
            // Crear un mensaje MIME
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Configurar destinatario, asunto y contenido
            helper.setTo(toEmail);
            helper.setSubject("Código de Verificación TicketPlay");
            helper.setText(buildEmailBody(otp), true); // Enviar contenido HTML

            // Enviar el correo
            mailSender.send(message);
            System.out.println("Correo enviado a: " + toEmail);
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }

    private String buildEmailBody(String otp) {
        return "<h1>Tu código de verificación para acceder a TicketPlay </h1>"
                + "<p>Tu código OTP es: <strong>" + otp + "</strong></p>"
                + "<p>Este código expirará en 5 minutos.</p>";
    }
}

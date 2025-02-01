package com.grupo.pag.pagadminapi.services;

import com.grupo.pag.pagadminapi.config.exceptionhandler.customexceptions.EnvioEmailException;
import com.grupo.pag.pagadminapi.util.email.TemplateEmailBuild;
import com.grupo.pag.pagadminapi.util.email.TipoTemplateEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final String TYPE = "text/html; charset=utf-8";

    @Value("${url.validacao-email}")
    private String urlToken;
    public void sendConfirmationEmail(String emailDestino, String nomeUsuario, String token) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nomeUsuario", nomeUsuario);
        params.put("url_token", String.format("%s?token=%s", urlToken, token));
        String bodyEmail = gerarTemplateEmail(params, TipoTemplateEmail.CONFIRMACAO_EMAIL);
        try {
            sendEmail("Confirmação de email", emailDestino, bodyEmail);
        } catch (MessagingException e) {
            System.out.println("??????");
            throw new EnvioEmailException("Ocorreu um erro no envio do email");
        }
    }

    private void sendEmail(String subject, String emailDestino, String bodyEmail) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        mimeMailMessage.setFrom(new InternetAddress(emailDestino));
        mimeMailMessage.setRecipients(MimeMessage.RecipientType.TO, emailDestino);
        mimeMailMessage.setSubject(subject);
        mimeMailMessage.setContent(bodyEmail, TYPE);
        javaMailSender.send(mimeMailMessage);
    }

    private String gerarTemplateEmail(Map<String, Object> params, TipoTemplateEmail tipoTemplateEmail) {
        var builder = new TemplateEmailBuild.Builder(tipoTemplateEmail).getInstance();
        params.entrySet().forEach(entry ->
                builder.setVariavel(entry.getKey(), entry.getValue())
        );
        return builder.getHtmlTemplate();
    }
}
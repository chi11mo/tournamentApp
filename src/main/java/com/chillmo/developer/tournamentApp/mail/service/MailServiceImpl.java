package com.chillmo.developer.tournamentApp.mail.service;

import com.chillmo.developer.tournamentApp.mail.domain.Mail;
import com.chillmo.developer.tournamentApp.user.domain.Token;
import com.chillmo.developer.tournamentApp.user.service.TokenService;
import com.chillmo.developer.tournamentApp.user.service.UserService;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


/**
 * Service to send mails with.
 */
@AllArgsConstructor
@Service("mailService")
public class MailServiceImpl implements MailService {
    @SuppressWarnings("checkstyle:ConstantName")
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    /**
     * mailSender.
     */

    /* default */ JavaMailSender mailSender;


    private final TokenService tokenService;
    private final UserService userService;








    @SuppressWarnings("PMD.LocalVariableCouldBeFinal")
    @Override
    public void sendValidationMail(final String email, final Token token) {
        final Mail mail = new Mail();
        mail.setContentType("text/html");
        String content = "<font size =\"5\" face=\"arial\">"
            + "Hallo,<br>"
            + "Bitte klicken Sie auf den Link um sich zu verifizieren:<br>"
            + "<h3><a href=\"[[URL]]\" target=\"_self\">Verifizieren</a></h3>"
            + "Vielen Dank,<br>"
            + "<br>Access 1-2."
            + "</font>";

        String verifyURL = "http://localhost:4201/validation-success/token/" + token.getTokenContent();
        content = content.replace("[[URL]]", verifyURL);

        mail.setMailContent(content);

        mail.setMailFrom(email);
        mail.setMailTo("Access@validation.de");
        mail.setMailSubject("Validierung");

        logger.info("mailsend");
        sendEmail(mail);
    }

    @Override
    public void sendEmail(Mail mail) {
        final MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "Access Team 1-2"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent(), true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.info(String.valueOf(e));
        }
    }

    @Override
    public void sendResetMail(final String emailAddress) {
        final Token token = tokenService.generateToken(userService.findUserByeMail(emailAddress));
        final String tokenString = token.getTokenContent();
        final Mail mail = new Mail();
        mail.setMailFrom("Access@validate.de");
        mail.setMailTo(emailAddress);
        mail.setMailSubject("");
        mail.setMailContent("Click on the following Link to Reset your password: "
            + "http://localhost:4201/password-reset/account?token=" + tokenString);
        sendEmail(mail);
    }
}

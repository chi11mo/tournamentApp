package com.chillmo.developer.tournamentApp.mail.service;


import com.chillmo.developer.tournamentApp.mail.domain.Mail;
import com.chillmo.developer.tournamentApp.user.domain.Token;

/**
 * Method to send an E-Mail.
 */
public interface MailService {
    void sendEmail(Mail mail);
    void sendValidationMail(String email, Token token);

    /**
     * This Method sends an Email to reset the password.
     *
     * @param email email of User
     */
    void sendResetMail(String email);

}

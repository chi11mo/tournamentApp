package com.chillmo.developer.tournamentApp.mail.domain;


import java.util.List;

/**
 * This class represents an E-Mail.
 */
public class Mail {
    private String mailFrom;

    private String mailTo;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private List<Object> attachments;

    public Mail() {
        contentType = "text/plain";
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(final String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(final String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(final String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(final String mailContent) {
        this.mailContent = mailContent;
    }

    public List<Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(final List<Object> attachments) {
        this.attachments = attachments;
    }

}

package com.gwy.manager.mail;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class MailForm {
    private String subject;
    private String text;
    private String from;
    private String to;
    private boolean html;

    public MailForm() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "MailForm{" +
                "subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}

package model.peoplesRoles;

import java.text.SimpleDateFormat;

public class Messege {
    String messege_subject;
    String messege_text;
    String date;
    String login;

    public Messege(String messege_subject, String messege_text, String date, String login) {
        this.messege_subject = messege_subject;
        this.messege_text = messege_text;
        this.date = date;
        this.login=login;
    }

    public String getMessege_subject() {
        return messege_subject;
    }

    public void setMessege_subject(String messege_subject) {
        this.messege_subject = messege_subject;
    }

    public String getMessege_text() {
        return messege_text;
    }

    public void setMessege_text(String messege_text) {
        this.messege_text = messege_text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

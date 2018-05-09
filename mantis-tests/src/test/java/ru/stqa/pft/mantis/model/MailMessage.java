package ru.stqa.pft.mantis.model;

/**
 * Created by elina_000 on 08.05.2018.
 */
public class MailMessage {
    public  String to;
    public String text;

    public MailMessage(String to, String text){
        this.to = to;
        this.text = text;
    }
}

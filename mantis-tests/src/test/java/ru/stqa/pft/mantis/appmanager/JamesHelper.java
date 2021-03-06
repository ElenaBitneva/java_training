package ru.stqa.pft.mantis.appmanager;

import javax.mail.*;
import java.io.InputStream;
import java.io.PrintStream;
import org.apache.commons.net.telnet.TelnetClient;

import static com.sun.deploy.perf.DeployPerfUtil.write;

/**
 * Created by elina_000 on 08.05.2018.
 */
public class JamesHelper {
    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailserver;

    public JamesHelper(ApplicationManager app) {
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());

    }

    public boolean doesUserExist(String name) {
        initTelnetSession();
        write("verify" + name);
        String result = readUntil("exist");
        closeTelnetSession();
        return result.trim().equals("User" + name + "exist");
    }

    private void closeTelnetSession() {
        write("quit");
    }

    public void createUser(String name, String passwd)

    {
        initTelnetSession();
        write("adduser " + name + "" + passwd);
        String result = readUntil("User" + name + " added");
        closeTelnetSession();
    }

    public void deleteUser(String name) {
        initTelnetSession();
        write("deluser " + name);
        String result = readUntil("User " + name + " deleted");
        closeTelnetSession();
    }


    private void initTelnetSession() {
        mailserver = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty("mailserver.adminlogin");
        String password = app.getProperty("mailserver.adminpassword");
        try {
            telnet.connect(mailserver, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        readUntil("Login id:");

        write("");

        readUntil("Password:");

        write("");


        readUntil("Login id:");

        write(login);

        readUntil("Password:");

        write(password);

        readUntil("Welcome " + login + ".HELP for a list of commands");

    }
    private String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();

                    }
                }
                ch = (char) in.read();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
         return  null;
    }
    private void write(String value){
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void drainEmails(String username, String password) throws MessagingException{
        Folder inbox = openInbox (username, password);
        for (Message message : inbox.getMessages()) {
            message.setFlag(Flags.Flag.DELETED, true);
        }
        closeFolder(inbox);
    }

    private Folder openInbox(String username, String password) {
        //// TODO: 08.05.2018
        return null;
    }

    private void closeFolder(Folder folder) throws MessagingException{
        folder.close(true);
        store.close();
    }
}
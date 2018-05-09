package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by elina_000 on 09.05.2018.
 */
public class ResetPasswordTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.mail().start();
    }
    @Test

    public void testResetPassword() throws IOException, MessagingException {
        HttpSession session = app.newSession();
        app.signIn().login(new UserData().withUsername("administrator").withPassword("root"));
        UserData testUser = new UserData().withUsername("test").withEmail("test@localhost.localdomain")
                .withPassword("root1");
        app.signIn().resetPassword(testUser);

        List<MailMessage> messages = app.mail().waitForMail(1, 20000);
       String confirmationLink = findConfirmationLink(messages, testUser.getEmail());
        app.signIn().resetPasswordByConfirmationLink(testUser, confirmationLink);

        assertTrue(session.login(testUser.getUsername(),testUser.getPassword()));
        assertTrue(session.isLoggedInAs(testUser.getUsername()));
    }
    private String findConfirmationLink(List<MailMessage>mailMessages,String email){
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void ensureAfterconditions(){
       app.mail().stop();
    }
}

package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.model.UserData;

/**
 * Created by elina_000 on 09.05.2018.
 */
public class SignInHelper extends BaseHelper {
    private ApplicationManager app;
    public SignInHelper(ApplicationManager applicationManager) {
        super (applicationManager.wd);
        app = applicationManager;
    }

    public void login(UserData userData){
        wd.get(app.getProperty("web.baseUrl")+ "/login_page.php");
        type(By.name("username"), userData.getUsername());
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"), userData.getPassword());
        click(By.xpath("//input[@type='submit']"));
    }
    public void resetPassword(UserData userData){
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
        click(By.linkText(userData.getUsername()));
        click(By.xpath("//input[@value='Reset Password']"));
    }
    public void resetPasswordByConfirmationLink(UserData userData, String url){
        wd.get(url);
        type(By.id("password"), userData.getPassword());
        type(By.id("password-confirm"), userData.getPassword());
        click(By.xpath("//button[@type='submit']"));

    }
}

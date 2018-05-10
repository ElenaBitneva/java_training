package ru.stqa.pft.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.UserData;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Objects;

/**
 * Created by elina_000 on 15.03.2018.
 */
public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    protected static final UserData administrator
            = new UserData().withUsername("administrator").withPassword("root");

    protected static final BigInteger project_id = BigInteger.valueOf(1);
    protected static final BigInteger page_number = BigInteger.valueOf(1);
    protected static final BigInteger per_page = BigInteger.valueOf(1000);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }
    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        URL mAddress = new URL("http://localhost/mantisbt-2.14.0/mantisbt-2.14.0/api/soap/mantisconnect.php");
        MantisConnectPortType mantisConnectLocator = new MantisConnectLocator()
                .getMantisConnectPort(mAddress);

        IssueData[] issues = mantisConnectLocator.mc_project_get_issues(administrator.getUsername(),
                administrator.getPassword(),
                project_id,
                page_number,
                per_page);

        BigInteger bigIntegerProjectId = BigInteger.valueOf(issueId);
        for (IssueData issue: issues) {
            if(bigIntegerProjectId.equals(issue.getId()) && !Objects.equals(issue.getStatus().getName(), "resolved")
                    && !Objects.equals(issue.getStatus().getName(), "closed")) {
                return true;
            }
        }

        return false;
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}

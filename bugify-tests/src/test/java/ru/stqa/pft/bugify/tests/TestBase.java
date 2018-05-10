package ru.stqa.pft.bugify.tests;

import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.bugify.appmanager.BugifyHelper;
import ru.stqa.pft.bugify.model.IssueModel;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by elina_000 on 15.03.2018.
 */
public class TestBase {
    @BeforeSuite
    public void setUp() throws Exception {
    }

    @AfterSuite
    public void tearDown() {
    }

    public void skipIfNotFixed(int issueId) throws IOException, URISyntaxException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException, URISyntaxException {
        // получаем открытые баги
        Stream<IssueModel> openIssues = BugifyHelper.getAllIssues()
                 .stream()
                 .filter(issueModel -> issueModel.state_name.equals("Open"));

        String id = String.valueOf(issueId);
        return openIssues.anyMatch(issueModel -> issueModel.id.equals(id));
    }
}


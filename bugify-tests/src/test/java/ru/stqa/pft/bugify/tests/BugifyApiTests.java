package ru.stqa.pft.bugify.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.rmi.RemoteException;

public class BugifyApiTests extends TestBase {
    @Test
    public void skipped() throws IOException, URISyntaxException {
        skipIfNotFixed(1427);
    }

    @Test
    public void pass() throws IOException, URISyntaxException {
        skipIfNotFixed(9999999);
    }
}

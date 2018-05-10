package ru.stqa.pft.bugify.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.bugify.model.IssueModel;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

public class BugifyHelper {
    private static final String bugifyApi =
            "28accbe43ea112d9feb328d2c00b3eed";

    private static Executor getExecutor(){
        return Executor.newInstance().auth(bugifyApi, "");
    }

    public static Set<IssueModel> getAllIssues() throws URISyntaxException, IOException {
        Request request = Request.Get(new URI("http://demo.bugify.com/api/issues.json"));
        String json = getExecutor().execute(request).returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonElement issues = parser.parse(json).getAsJsonObject().get("issues");
        TypeToken<Set<IssueModel>> typeToken = new TypeToken<Set<IssueModel>>(){};
        return new Gson().fromJson(issues, typeToken.getType());
    }
}


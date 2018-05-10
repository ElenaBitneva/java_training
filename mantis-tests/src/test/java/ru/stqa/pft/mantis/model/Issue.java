package ru.stqa.pft.mantis.model;

/**
 * Created by elina_000 on 10.05.2018.
 */
public class Issue {
    private int id;
    private String Status;

    public String getStatus() {
        return Status;
    }

    public Issue withStatus(String status) {
        Status = status;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }
}

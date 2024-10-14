package com.example.application.services;

import java.io.Serializable;

public abstract class SerializableDashboardItem implements Serializable {
    private final int id;
    private final String title;

    protected SerializableDashboardItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

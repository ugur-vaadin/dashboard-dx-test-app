package com.example.application.services;

import java.io.Serializable;

public abstract class SerializableDashboardItem implements Serializable {
    private final String title;

    protected SerializableDashboardItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

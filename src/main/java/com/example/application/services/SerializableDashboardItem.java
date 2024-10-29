package com.example.application.services;

import java.io.Serializable;

public abstract class SerializableDashboardItem implements Serializable {
    private String title;

    protected SerializableDashboardItem(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

package com.example.application.services;

import java.util.Arrays;
import java.util.List;

public class SerializableDashboardSection extends SerializableDashboardItem {
    private final List<SerializableDashboardSection> children;

    public SerializableDashboardSection(int id, String title, SerializableDashboardSection... children) {
        super(id, title);
        this.children = Arrays.asList(children);
    }

    public List<SerializableDashboardSection> getChildren() {
        return children;
    }
}

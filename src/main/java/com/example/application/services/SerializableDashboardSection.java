package com.example.application.services;

import java.util.Arrays;
import java.util.List;

public class SerializableDashboardSection extends SerializableDashboardItem {
    private final List<SerializableDashboardWidget> children;

    public SerializableDashboardSection(int id, String title, SerializableDashboardWidget... children) {
        super(id, title);
        this.children = Arrays.asList(children);
    }

    public List<SerializableDashboardWidget> getChildren() {
        return children;
    }
}

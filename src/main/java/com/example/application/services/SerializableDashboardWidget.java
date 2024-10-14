package com.example.application.services;

public class SerializableDashboardWidget extends SerializableDashboardItem {
    private final Integer rowspan;
    private final Integer colspan;

    public SerializableDashboardWidget(int id, String title, Integer rowspan, Integer colspan) {
        super(id, title);
        this.rowspan = rowspan;
        this.colspan = colspan;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public Integer getColspan() {
        return colspan;
    }
}

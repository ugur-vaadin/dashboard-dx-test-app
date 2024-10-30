package com.example.application.services;

import java.io.Serializable;
import java.util.List;

public class SerializableDashboardItem implements Serializable {
    private String title;
    // TODO maybe use itemid
    private String widgetId;
    private WidgetType widgetType;
    private Integer rowspan;
    private Integer colspan;
    private String importantData;
    // TODO make sure not null for sections
    private List<SerializableDashboardItem> items;

    public boolean isSection() {
        return items != null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SerializableDashboardItem> getItems() {
        return items;
    }

    public void setItems(List<SerializableDashboardItem> items) {
        this.items = items;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(WidgetType widgetType) {
        this.widgetType = widgetType;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public void setRowspan(Integer rowspan) {
        this.rowspan = rowspan;
    }

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }

    public String getImportantData() {
        return importantData;
    }

    public void setImportantData(String importantData) {
        this.importantData = importantData;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }
}

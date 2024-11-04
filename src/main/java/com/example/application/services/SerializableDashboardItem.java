package com.example.application.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaadin.hilla.Nullable;

import java.io.Serializable;
import java.util.List;

public class SerializableDashboardItem implements Serializable {
    @Nullable
    private String title;
    @Nullable
    private String widgetId;
    @Nullable
    private String widgetType;
    @Nullable
    private Integer rowspan;
    @Nullable
    private Integer colspan;
    @Nullable
    private String importantData;
    @Nullable
    private List<SerializableDashboardItem> items;

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

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
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

    @JsonIgnore
    public boolean isSection() {
        return items != null;
    }
}

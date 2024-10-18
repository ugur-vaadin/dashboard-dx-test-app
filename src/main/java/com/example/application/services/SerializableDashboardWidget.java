package com.example.application.services;

public class SerializableDashboardWidget extends SerializableDashboardItem {
    private WidgetType widgetType;
    private Integer rowspan;
    private Integer colspan;

    public SerializableDashboardWidget(String title) {
        super(title);
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
}

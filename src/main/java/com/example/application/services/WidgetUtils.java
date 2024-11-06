package com.example.application.services;

import com.vaadin.flow.component.Component;

public class WidgetUtils {
    public static CustomWidget getPredefinedWidget(SerializableDashboardItem serializableDashboardItem) {
        if (serializableDashboardItem.isSection()) {
            return null;
        }
        CustomWidget dashboardWidget = new CustomWidget();
        dashboardWidget.setWidgetId(serializableDashboardItem.getWidgetId());
        dashboardWidget.setTitle(serializableDashboardItem.getTitle());
        if (serializableDashboardItem.getWidgetType() != null) {
            WidgetType widgetType = WidgetType.valueOf(serializableDashboardItem.getWidgetType());
            dashboardWidget.setWidgetType(widgetType);
            dashboardWidget.setContent(getPredefinedWidgetContent(widgetType));
        }
        if (serializableDashboardItem.getColspan() != null) {
            dashboardWidget.setColspan(serializableDashboardItem.getColspan());
        }
        if (serializableDashboardItem.getRowspan() != null) {
            dashboardWidget.setRowspan(serializableDashboardItem.getRowspan());
        }
        dashboardWidget.setImportantData(serializableDashboardItem.getImportantData());
        return dashboardWidget;
    }

    public static SerializableDashboardItem widgetToSerializableItem(CustomWidget widget) {
        SerializableDashboardItem serializableDashboardWidget = new SerializableDashboardItem();
        serializableDashboardWidget.setTitle(widget.getTitle());
        serializableDashboardWidget.setColspan(widget.getColspan());
        serializableDashboardWidget.setRowspan(widget.getRowspan());
        serializableDashboardWidget.setWidgetId(widget.getWidgetId());
        serializableDashboardWidget.setImportantData(widget.getImportantData());
        serializableDashboardWidget.setWidgetType(widget.getWidgetType() == null ? null: widget.getWidgetType().name());
        return serializableDashboardWidget;
    }

    public static Component getPredefinedWidgetContent(WidgetType widgetType) {
        return switch (widgetType) {
            case AREA_CHART -> PredefinedCharts.getAreaChart();
            case AREA_SPLINE_CHART -> PredefinedCharts.getAreaSplineChart();
            case BAR_CHART -> PredefinedCharts.getBarChart();
            case COLUMN_CHART -> PredefinedCharts.getColumnChart();
            case PIE_CHART -> PredefinedCharts.getPieChart();
            case SCATTER_CHART -> PredefinedCharts.getScatterChart();
        };
    }
}

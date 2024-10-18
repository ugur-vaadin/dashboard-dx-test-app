package com.example.application.services;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dashboard.DashboardWidget;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import elemental.json.JsonArray;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@BrowserCallable
@AnonymousAllowed
@Service
public class DataPersistence implements Serializable {

    private List<SerializableDashboardItem> items;

    private DataPersistence() {
        initItems();
    }

    @ClientCallable
    public void storeJsonItems(JsonArray jsonItems) {
        // NO-OP
    }

    public void storeItems(List<SerializableDashboardItem> items) {
        // NO-OP
    }

    public List<SerializableDashboardItem> getItems() {
        return items;
    }

    @ClientCallable
    public void updateImportantData(String importantData) {
        // NO-OP
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

    public static DashboardWidget getPredefinedWidget(SerializableDashboardWidget serializableDashboardWidget) {
        DashboardWidget dashboardWidget = new DashboardWidget();
        dashboardWidget.setContent(getPredefinedWidgetContent(serializableDashboardWidget.getWidgetType()));
        if (serializableDashboardWidget.getColspan() != null) {
            dashboardWidget.setColspan(serializableDashboardWidget.getColspan());
        }
        if (serializableDashboardWidget.getRowspan() != null) {
            dashboardWidget.setRowspan(serializableDashboardWidget.getRowspan());
        }
        dashboardWidget.setTitle(serializableDashboardWidget.getTitle());
        return dashboardWidget;
    }

    private void initItems() {
        items = new ArrayList<>();

        SerializableDashboardWidget widget1 = new SerializableDashboardWidget("Widget 1");
        widget1.setWidgetType(WidgetType.AREA_CHART);
        widget1.setColspan(2);
        items.add(widget1);

        SerializableDashboardWidget widget2 = new SerializableDashboardWidget("Widget 2");
        widget2.setWidgetType(WidgetType.BAR_CHART);
        widget2.setRowspan(2);
        items.add(widget2);

        SerializableDashboardWidget widgetInSection1 = new SerializableDashboardWidget("Widget in section 1");
        widgetInSection1.setWidgetType(WidgetType.AREA_SPLINE_CHART);
        SerializableDashboardWidget widgetInSection2 = new SerializableDashboardWidget("Widget in section 2");
        widgetInSection2.setWidgetType(WidgetType.SCATTER_CHART);
        SerializableDashboardSection section = new SerializableDashboardSection("Section", widgetInSection1, widgetInSection2);
        items.add(section);
    }
}

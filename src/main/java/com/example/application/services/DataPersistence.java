package com.example.application.services;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
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

    public void storeItems(List<SerializableDashboardItem> items) {
        this.items = items;
    }


    public List<SerializableDashboardItem> getItems() {
        return items;
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
    };

    private void initItems() {
        items = new ArrayList<>();

        SerializableDashboardItem widget1 = new SerializableDashboardItem();
        widget1.setTitle("Widget 1");
        widget1.setWidgetId("server-widget-1");
        widget1.setWidgetType(WidgetType.AREA_CHART.name());
        widget1.setColspan(2);
        items.add(widget1);

        SerializableDashboardItem widget2 = new SerializableDashboardItem();
        widget2.setTitle("Widget 2");
        widget2.setWidgetId("server-widget-2");
        widget2.setWidgetType(WidgetType.BAR_CHART.name());
        widget2.setRowspan(2);
        items.add(widget2);

        SerializableDashboardItem widgetInSection1 = new SerializableDashboardItem();
        widgetInSection1.setTitle("Widget in section 1");
        widgetInSection1.setWidgetId("server-widget-3");
        widgetInSection1.setWidgetType(WidgetType.AREA_SPLINE_CHART.name());
        SerializableDashboardItem widgetInSection2 = new SerializableDashboardItem();
        widgetInSection2.setTitle("Widget in section 2");
        widgetInSection2.setWidgetId("server-widget-4");
        widgetInSection2.setWidgetType(WidgetType.SCATTER_CHART.name());

        SerializableDashboardItem section = new SerializableDashboardItem();
        section.setTitle("Section");
        List<SerializableDashboardItem> sectionItems = new ArrayList<>();
        sectionItems.add(widgetInSection1);
        sectionItems.add(widgetInSection2);
        section.setItems(sectionItems);
        items.add(section);

        SerializableDashboardItem widgetWithTextField = new SerializableDashboardItem();
        widgetWithTextField.setTitle("Widget with text field");
        widgetWithTextField.setWidgetId("server-widget-with-text-field");
        widgetWithTextField.setImportantData("Initial important data");
        items.add(widgetWithTextField);
    }
}

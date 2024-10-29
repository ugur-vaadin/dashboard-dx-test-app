package com.example.application.services;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@BrowserCallable
@AnonymousAllowed
@Service
public class DataPersistence implements Serializable {

    private List<SerializableDashboardItem> items;

    private String jsonItems;

    private DataPersistence() {
        initItems();
        initJsonItems();
    }

    public void storeJsonItems(String jsonItems) {
        this.jsonItems = jsonItems;
    }

    public void storeItems(List<SerializableDashboardItem> items) {
        this.items = items;
    }

    public String getJsonItems() {
        return jsonItems;
    }

    public List<SerializableDashboardItem> getItems() {
        return items;
    }

    public void updateImportantDataClient(String id, String importantData) {
        Objects.requireNonNull(id);
        jsonItems = updateJsonArray(id, importantData).toJson();
    }

    public void updateImportantDataServer(String id, String importantData) {
        Objects.requireNonNull(id);
        items.stream().map(item -> {
            if (item instanceof SerializableDashboardSection serializableDashboardSection) {
                return serializableDashboardSection.getChildren().stream().filter(serializableDashboardWidget ->
                        id.equals(serializableDashboardWidget.getWidgetId())).findAny().orElse(null);
            } else {
                SerializableDashboardWidget serializableDashboardWidget = (SerializableDashboardWidget) item;
                if (id.equals(serializableDashboardWidget.getWidgetId())) {
                    return serializableDashboardWidget;
                }
            }
            return null;
        }).filter(Objects::nonNull).findAny().ifPresent(serializableDashboardWidget -> serializableDashboardWidget.setImportantData(importantData));
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

    public static CustomWidget getPredefinedWidget(SerializableDashboardWidget serializableDashboardWidget) {
        CustomWidget dashboardWidget = new CustomWidget();
        dashboardWidget.setWidgetId(serializableDashboardWidget.getWidgetId());
        dashboardWidget.setTitle(serializableDashboardWidget.getTitle());
        if (serializableDashboardWidget.getWidgetType() != null) {
            dashboardWidget.setWidgetType(serializableDashboardWidget.getWidgetType());
            dashboardWidget.setContent(getPredefinedWidgetContent(serializableDashboardWidget.getWidgetType()));
        }
        if (serializableDashboardWidget.getColspan() != null) {
            dashboardWidget.setColspan(serializableDashboardWidget.getColspan());
        }
        if (serializableDashboardWidget.getRowspan() != null) {
            dashboardWidget.setRowspan(serializableDashboardWidget.getRowspan());
        }
        dashboardWidget.setImportantData(serializableDashboardWidget.getImportantData());
        return dashboardWidget;
    }

    private void initItems() {
        items = new ArrayList<>();

        SerializableDashboardWidget widget1 = new SerializableDashboardWidget("Widget 1");
        widget1.setWidgetId("server-widget-1");
        widget1.setWidgetType(WidgetType.AREA_CHART);
        widget1.setColspan(2);
        items.add(widget1);

        SerializableDashboardWidget widget2 = new SerializableDashboardWidget("Widget 2");
        widget2.setWidgetId("server-widget-2");
        widget2.setWidgetType(WidgetType.BAR_CHART);
        widget2.setRowspan(2);
        items.add(widget2);

        SerializableDashboardWidget widgetInSection1 = new SerializableDashboardWidget("Widget in section 1");
        widgetInSection1.setWidgetId("server-widget-3");
        widgetInSection1.setWidgetType(WidgetType.AREA_SPLINE_CHART);
        SerializableDashboardWidget widgetInSection2 = new SerializableDashboardWidget("Widget in section 2");
        widgetInSection2.setWidgetId("server-widget-4");
        widgetInSection2.setWidgetType(WidgetType.SCATTER_CHART);
        SerializableDashboardSection section = new SerializableDashboardSection("Section", widgetInSection1, widgetInSection2);
        items.add(section);

        SerializableDashboardWidget widgetWithTextField = new SerializableDashboardWidget("Widget with text field");
        widgetWithTextField.setWidgetId("server-widget-with-text-field");
        widgetWithTextField.setImportantData("Initial important data");
        items.add(widgetWithTextField);
    }

    private void initJsonItems() {
        JsonArray jsonItemsArray = Json.createArray();

        JsonObject item1 = Json.createObject();
        item1.put("id", "client-widget-1");
        item1.put("title", "Widget 1");
        item1.put("type", "column");
        item1.put("importantData", "Important 1");
        jsonItemsArray.set(0, item1);

        JsonObject item2 = Json.createObject();
        item2.put("id", "client-widget-2");
        item2.put("title", "Widget 2");
        item2.put("type", "line");
        item2.put("importantData", "Important 2");
        jsonItemsArray.set(1, item2);

        JsonObject section = Json.createObject();
        section.put("id", "client-section");
        section.put("title", "Section");
        JsonArray sectionItems = Json.createArray();
        section.put("items", sectionItems);
        jsonItemsArray.set(2, section);

        JsonObject itemInSection1 = Json.createObject();
        itemInSection1.put("id", "client-widget-3");
        itemInSection1.put("title", "Widget in section 1");
        itemInSection1.put("type", "column");
        itemInSection1.put("importantData", "Important 3");
        sectionItems.set(0, itemInSection1);

        JsonObject itemInSection2 = Json.createObject();
        itemInSection2.put("id", "client-widget-4");
        itemInSection2.put("title", "Widget in section 2");
        itemInSection2.put("type", "line");
        itemInSection2.put("importantData", "Important 4");
        sectionItems.set(1, itemInSection2);

        jsonItems = jsonItemsArray.toJson();
    }

    private JsonArray updateJsonArray(String id, String importantData) {
        JsonArray itemsArray = (JsonArray) Json.parse(jsonItems);
        for (int i = 0; i < itemsArray.length(); i++) {
            JsonObject item = itemsArray.getObject(i);
            if (item.hasKey("items")) {
                JsonArray itemsInSection = item.getArray("items");
                for (int j = 0; j < itemsInSection.length(); j++) {
                    JsonObject itemInSection = itemsInSection.getObject(j);
                    if (id.equals(itemInSection.get("id"))) {
                        item.put("importantData", importantData);
                        return itemsArray;
                    }
                }
            } else if (id.equals(item.get("id"))) {
                item.put("importantData", importantData);
                return itemsArray;
            }
        }
        return itemsArray;
    }
}

package com.example.application.services;

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

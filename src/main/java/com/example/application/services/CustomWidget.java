package com.example.application.services;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.dashboard.DashboardWidget;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

public class CustomWidget extends DashboardWidget {

    private WidgetType widgetType;

    private final TextField textField;

    private String widgetId;

    public CustomWidget() {
        setTitle("Custom Widget");
        textField = new TextField();
        textField.setTitle("Important data");
        setContent(textField);
    }

    public String getImportantData() {
        return textField.getValue();
    }

    public void setImportantData(String data) {
        textField.setValue(data == null ? "" : data);
    }

    public Registration addImportantDataChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> listener) {
        return textField.addValueChangeListener(listener);
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(WidgetType widgetType) {
        this.widgetType = widgetType;
    }
}

package com.example.application.services;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.dashboard.DashboardWidget;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

public class CustomWidget extends DashboardWidget {

    private final TextField textField;

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
        textField.setValue(data);
    }

    public Registration addImportantDataChangeListener(HasValue.ValueChangeListener<? super AbstractField.ComponentValueChangeEvent<TextField, String>> listener) {
        return textField.addValueChangeListener(listener);
    }
}

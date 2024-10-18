package com.example.application.services;

import com.vaadin.flow.component.dashboard.DashboardWidget;
import com.vaadin.flow.component.textfield.TextField;

public class CustomWidget extends DashboardWidget {

    public CustomWidget() {
        setTitle("Custom Widget");
        TextField textField = new TextField();
        textField.setTitle("Important data");
        setContent(textField);
    }
}

package com.example.application.solutions;

import com.example.application.services.PredefinedCharts;
import com.example.application.services.CustomWidget;
import com.example.application.services.DataPersistence;
import com.example.application.services.SerializableDashboardItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dashboard.Dashboard;
import com.vaadin.flow.component.dashboard.DashboardSection;
import com.vaadin.flow.component.dashboard.DashboardWidget;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Solutions to the Flow component tasks
 */
public class FlowSolutions extends HorizontalLayout {

    public FlowSolutions(DataPersistence dataPersistence) {
        /*
         * *******************************************
         * TASK 1: Basic Setup and Initialization
         * *******************************************
         */

        // Subtask 1.1: Add a dashboard with two widgets and two grouped
        //      widgets to the sample application.
        Dashboard dashboard = new Dashboard();
        dashboard.setSizeFull();
        add(dashboard);

        DashboardWidget widget1 = new DashboardWidget();
        DashboardWidget widget2 = new DashboardWidget();
        dashboard.add(widget1, widget2);

        DashboardSection section = dashboard.addSection();
        DashboardWidget widgetInSection1 = new DashboardWidget();
        DashboardWidget widgetInSection2 = new DashboardWidget();
        section.add(widgetInSection1, widgetInSection2);

        /*
         * Subtask 1.2: Set title and content to the components. You can
         *      use the provided charts in “PredefinedCharts” or any other
         *      custom components to create widgets.
         */
        widget1.setTitle("Widget 1");
        widget1.setContent(PredefinedCharts.getBarChart());
        TextField widgetTextField  = new TextField();
        widget2.setContent(widgetTextField);
        widget2.setTitle("Widget 2");

        section.setTitle("Section");
        widgetInSection1.setContent(PredefinedCharts.getAreaChart());
        widgetInSection1.setTitle("Widget in section 1");
        Button widgetInSectionButton  = new Button();
        widgetInSection2.setContent(widgetInSectionButton);
        widgetInSection2.setTitle("Widget in section 2");

        // Subtask 1.3: Place a span with text next to the title of a widget.
        Span headerContent = new Span("Header content");
        widget1.setHeaderComponent(headerContent);

        /*
         * Subtask 1.4: Use the data from the method "DataPersistence.getItems"
         *      to populate the dashboard.
         */
        List<SerializableDashboardItem> dataPersistenceItems = dataPersistence.getItems();
        dataPersistenceItems.forEach(item -> {
            if (item.isSection()) {
                DashboardSection dashboardSection = dashboard.addSection(item.getTitle());
                item.getItems().stream().map(DataPersistence::getPredefinedWidget).forEach(dashboardSection::add);
            } else {
                CustomWidget dashboardWidget = DataPersistence.getPredefinedWidget(item);
                dashboard.add(dashboardWidget);
            }
        });

        /*
         * Subtask 1.5: Preserve the current layout of the widgets within the
         *      dashboard whenever the value of the text field in the widget is
         *      updated. You can use the provided “DataPersistence.storeItems” and
         *      “DataPersistence.widgetToSerializableItem” methods.
         */
        Function<Component, SerializableDashboardItem> itemToSerializableItem = item -> {
            SerializableDashboardItem serializableDashboardItem;
            if (item instanceof DashboardSection dashboardSection) {
                List<SerializableDashboardItem> serializableWidgets = dashboardSection.getWidgets().stream()
                        .map(CustomWidget.class::cast)
                        .map(DataPersistence::widgetToSerializableItem)
                        .collect(Collectors.toCollection(ArrayList::new));
                serializableDashboardItem =  new SerializableDashboardItem();
                serializableDashboardItem.setTitle(dashboardSection.getTitle());
                serializableDashboardItem.setItems(serializableWidgets);
            } else {
                serializableDashboardItem = DataPersistence.widgetToSerializableItem((CustomWidget) item);
            }
            return serializableDashboardItem;
        };

        List<DashboardWidget> widgets = dashboard.getWidgets();
        CustomWidget widgetWithTextField = (CustomWidget) widgets.get(widgets.size() - 1);
        widgetWithTextField.addImportantDataChangeListener(e -> {
            List<SerializableDashboardItem> serializableDashboardItems = dashboard.getChildren()
                    .map(itemToSerializableItem).toList();
            dataPersistence.storeItems(serializableDashboardItems);
        });

        /*
         * *******************************************
         * TASK 2: Resizing Widgets
         * *******************************************
         */

        // Subtask 2.1: Resize a widget using a mouse. Dashboard should be
        //      configured for that purpose.
        dashboard.setEditable(true);
        // Resizing is done on the UI

        // Subtask 2.2: Resize a widget using the keyboard.
        // Resizing is done on the UI

        /*
         * *******************************************
         * TASK 3: Moving Items
         * *******************************************
         */

        // Subtask 3.1: Move a widget anywhere within the dashboard with a mouse.
        // Moving is done on the UI

        // Subtask 3.2: Move a widget in the section using the keyboard.
        // Moving is done on the UI

        // Subtask 3.3: Move the section either using the mouse or the keyboard.
        // Moving is done on the UI

        // Subtask 3.4: Move a widget out of the section on a button click.
        // Moving cannot be done on the UI
        NativeButton moveWidgetOutOfSection = new NativeButton("Move widget out of section");
        moveWidgetOutOfSection.addClickListener(click -> {
            DashboardWidget widgetToMove =  dashboard.getChildren()
                    .filter(DashboardSection.class::isInstance)
                    .map(DashboardSection.class::cast)
                    .map(DashboardSection::getWidgets)
                    .filter(widgetsInSection -> !widgetsInSection.isEmpty())
                    .map(widgetsInSection -> widgetsInSection.get(0))
                    .findAny().get();
            dashboard.addWidgetAtIndex(0, widgetToMove);
        });
        add(moveWidgetOutOfSection);

        /*
         * Subtask 3.5: Preserve the current layout of the widgets within the
         *      dashboard whenever an item is moved.
         */
        dashboard.addItemMovedListener(e -> {
            List<SerializableDashboardItem> serializableDashboardItems = e.getItems().stream()
                    .map(itemToSerializableItem).toList();
            dataPersistence.storeItems(serializableDashboardItems);
        });

        /*
         * *******************************************
         * TASK 4: Automatically Filling Empty Space
         * *******************************************
         */

        /*
         * Subtask 4.1: Place the section after the first widget so that there is
         *      space left next to the first widget. Make the dashboard
         *      automatically fill in the empty space with suitable widgets.
         */
        // Moving is done on the UI
        dashboard.setDenseLayout(true);

        /*
         * *******************************************
         * TASK 5: Layout and Styling
         * *******************************************
         */

        // Subtask 5.1: Remove the gap between the widgets.
        dashboard.setSpacing("0px");

        // Subtask 5.2: Limit the maximum number of widgets in the same row to 2.
        dashboard.setMaximumColumnCount(2);

        /*
         * Subtask 5.3: Make it so that the widgets in the dashboard are at least
         *       200px in height and width.
         */
        dashboard.setMaximumColumnWidth("200px");
        dashboard.setMinimumColumnWidth("200px");

        // Subtask 5.4: Change the color of the remove button to red.
        // Add the following to the styles file:
        /*
         * vaadin-dashboard-widget::part(remove-button) {
         *   color: red;
         * }
         */
    }
}

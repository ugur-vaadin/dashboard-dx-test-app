package com.example.application.views.solutions;

import com.example.application.services.PredefinedCharts;
import com.example.application.services.CustomWidget;
import com.example.application.services.DataPersistence;
import com.example.application.services.SerializableDashboardItem;
import com.example.application.services.SerializableDashboardSection;
import com.example.application.services.SerializableDashboardWidget;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dashboard.Dashboard;
import com.vaadin.flow.component.dashboard.DashboardSection;
import com.vaadin.flow.component.dashboard.DashboardWidget;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;

/**
 * Solutions to the Flow component tasks
 */
@PageTitle("Flow Solutions")
@Menu(icon = "line-awesome/svg/globe-solid.svg", order = 0)
@Route("solutions")
public class FlowSolutions extends HorizontalLayout {

    @Autowired
    public FlowSolutions(DataPersistence dataPersistence) {
        /*
         * *******************************************
         * TASK 1: Basic Setup and Initialization
         * *******************************************
         */

        // Subtask 1.1: Add the dashboard to the sample application.
        Dashboard dashboard = new Dashboard();
        add(dashboard);

        // Subtask 1.2: Set up two widgets and a section with two widgets.
        DashboardWidget widget1 = new DashboardWidget();
        DashboardWidget widget2 = new DashboardWidget();
        dashboard.add(widget1, widget2);

        DashboardSection section = dashboard.addSection();
        DashboardWidget widgetInSection1 = new DashboardWidget();
        DashboardWidget widgetInSection2 = new DashboardWidget();
        section.add(widgetInSection1, widgetInSection2);

        /*
         * Subtask 1.3: Set title and content to the items. You can use
         *      the provided charts in "PredefinedCharts" or any other
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

        // Subtask 1.4: Place a span with text next to the title of a widget.
        Span headerContent = new Span("Header content");
        widget1.setHeaderComponent(headerContent);

        /*
         * Subtask 1.5: Add the predefined CustomWidget to the dashboard.
         *      Preserve the value of the text field in the widget
         *      whenever it is updated. You can use the provided
         *      "DataPersistence.updateImportantData" method.
         */
        CustomWidget customWidget = new CustomWidget();
        dashboard.add(customWidget);
        TextField importantDataField = (TextField) customWidget.getContent();
        importantDataField.addValueChangeListener(e -> dataPersistence.updateImportantData(e.getValue()));

        /*
         * *******************************************
         * TASK 2: Resizing Widgets
         * *******************************************
         */

        // Subtask 2.1: Define varying initial widget sizes.
        widget1.setRowspan(2);
        widgetInSection2.setColspan(2);

        // Subtask 2.2: Resize a widget using a mouse.
        dashboard.setEditable(true);
        // Resizing is done on the UI

        // Subtask 2.3: Resize a widget using the keyboard.
        // Resizing is done on the UI

        // Subtask 2.4: Make sure that the height of the first
        //      widget cannot be changed.
        dashboard.addItemResizedListener(e -> {
            DashboardWidget resizedItem = e.getItem();
            if (resizedItem.equals(widget1) || resizedItem.getRowspan() != 2) {
                resizedItem.setRowspan(2);
            }
        });

        /*
         * *******************************************
         * TASK 3: Moving Items
         * *******************************************
         */

        // Subtask 3.1: Move a widget anywhere within the dashboard with a mouse.
        // Moving is done on the UI

        // Subtask 3.2: Move a widget in the section using the keyboard.
        // Moving is done on the UI

        // Subtask 3.3: Move the section.
        // Moving is done on the UI

        // Subtask 3.4: Move a widget out of the section.
        // Moving cannot be done on the UI
        dashboard.addWidgetAtIndex(2, widgetInSection1);

        /*
         * Subtask 3.5: Preserve the current layout of the widgets within the
         *      dashboard in the DB. You can use the provided
         *      “DataPersistence.storeItems” method.
         */
        Function<DashboardWidget, SerializableDashboardWidget> widgetToSerializableWidget = widget -> {
            SerializableDashboardWidget serializableDashboardWidget = new SerializableDashboardWidget(widget.getTitle());
            serializableDashboardWidget.setColspan(widget.getColspan());
            serializableDashboardWidget.setRowspan(widget.getRowspan());
            return serializableDashboardWidget;
        };

        Function<Component, SerializableDashboardItem> itemToSerializableItem = item -> {
            SerializableDashboardItem serializableDashboardItem;
            if (item instanceof DashboardSection dashboardSection) {
                SerializableDashboardWidget[] serializableWidgets = dashboardSection.getWidgets().stream().map(widgetToSerializableWidget).toArray(SerializableDashboardWidget[]::new);
                serializableDashboardItem =  new SerializableDashboardSection(dashboardSection.getTitle(), serializableWidgets);
            } else {
                serializableDashboardItem = widgetToSerializableWidget.apply((DashboardWidget) item);
            }
            return serializableDashboardItem;
        };

        List<SerializableDashboardItem> serializableDashboardItems = dashboard.getChildren().map(itemToSerializableItem).toList();
        dataPersistence.storeItems(serializableDashboardItems);

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
         * TASK 5: Removing Items
         * *******************************************
         */

        // Subtask 5.1: Remove a widget programmatically.
        dashboard.remove(widget2);

        // Subtask 5.2: Remove a widget using the UI.
        // Removing is done on the UI

        // Subtask 5.3: Remove a section using the UI.
        // Removing is done on the UI

        /*
         * Subtask 5.4: Remove all items programmatically and use the data from
         *      the method "DataPersistence.getItems" to populate the dashboard.
         *      You can use "DataPersistence.getPredefinedWidget" to convert
         *      serialized widget data to a widget.
         */
        dashboard.removeAll();
        List<SerializableDashboardItem> dataPersistenceItems = dataPersistence.getItems();
        dataPersistenceItems.forEach(item -> {
            if (item instanceof SerializableDashboardSection serializableDashboardSection) {
                DashboardSection dashboardSection = dashboard.addSection(serializableDashboardSection.getTitle());
                serializableDashboardSection.getChildren().stream().map(DataPersistence::getPredefinedWidget).forEach(dashboardSection::add);
            } else {
                SerializableDashboardWidget serializableDashboardWidget = (SerializableDashboardWidget) item;
                DashboardWidget dashboardWidget = DataPersistence.getPredefinedWidget(serializableDashboardWidget);
                dashboard.add(dashboardWidget);
            }
        });

        /*
         * *******************************************
         * TASK 6: Layout and Styling
         * *******************************************
         */

        // Subtask 6.1: Remove the gap between the widgets.
        dashboard.setSpacing("0px");

        // Subtask 6.2: Limit the maximum number of widgets in the same row to 2.
        dashboard.setMaximumColumnCount(2);

        /*
         * Subtask 6.3: Make it so that the widgets in the dashboard are at least
         *       400px in height and width.
         */
        dashboard.setMaximumColumnWidth("400px");
        dashboard.setMinimumColumnWidth("400px");

        // Subtask 6.4: Change the color of the remove button to red.
        // Add the following to the styles file:
        /*
         * vaadin-dashboard-widget::part(remove-button) {
         *   color: red;
         * }
         */
    }
}

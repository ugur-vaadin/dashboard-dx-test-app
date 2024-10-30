package com.example.application.views;

import com.example.application.services.CustomWidget;
import com.example.application.services.DataPersistence;
import com.example.application.services.SerializableDashboardItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dashboard.Dashboard;
import com.vaadin.flow.component.dashboard.DashboardSection;
import com.vaadin.flow.component.dashboard.DashboardWidget;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * *******************************************
 * TASK 1: Basic Setup and Initialization
 * *******************************************
 *
 * -1: Add a dashboard with two widgets and a section with
 *      two widgets to the sample application.
 * -2: Set title and content to the components. You can use
 *      the provided charts in “PredefinedCharts” or any other
 *      custom components to create widgets.
 * -3: Place a span with text next to the title of a widget.
 * -4: Use the data from the method "DataPersistence.getItems"
 *      to populate the dashboard.
 * -5: Preserve the current layout of the widgets within the
 *      dashboard whenever the value of the text field in the
 *      widget is updated. You can use the provided
 *      “DataPersistence.storeItems” method.
 *
 * *******************************************
 * TASK 2: Resizing Widgets
 * *******************************************
 *
 * -1: Make the first widget consume 2 rows and 3 columns in
 *      the dashboard's layout.
 * -2: Resize a widget using a mouse. Dashboard should be
 *      configured for that purpose.
 * -3: Resize a widget using the keyboard.
 * -4: Preserve the current layout of the widgets within the
 *      dashboard whenever a widget is resized.
 *
 * *******************************************
 * TASK 3: Moving Items
 * *******************************************
 *
 * -1: Move a widget anywhere within the dashboard with a mouse.
 * -2: Move a widget in the section using the keyboard.
 * -3: Move the section either using the mouse or the keyboard.
 * -4: Move a widget out of the section on a button click.
 * -5: Preserve the current layout of the widgets within the
 *      dashboard whenever an item is moved.
 *
 * *******************************************
 * TASK 4: Automatically Filling Empty Space
 * *******************************************
 *
 * -1: Place the section after the first widget so that there is
 *      space left next to the first widget. Make the dashboard
 *      automatically fill in the empty space with suitable widgets.
 *
 * *******************************************
 * TASK 5: Removing Items
 * *******************************************
 *
 * -1: Remove the first widget using the Dashboard API on a button
 *      click.
 * -2: Remove a widget using the UI.
 * -3: Remove a section using the UI.
 * -4: Remove all items programmatically.
 *
 * *******************************************
 * TASK 6: Layout and Styling
 * *******************************************
 *
 * -1: Remove the gap between the widgets.
 * -2: Limit the maximum number of widgets in the same row to 2.
 * -3: Make it so that the widgets in the dashboard are at least
 *       400px in height and width.
 * -4: Change the color of the remove button to red.
 */
@PageTitle("Flow View")
@Menu(icon = "line-awesome/svg/globe-solid.svg", order = 0)
@Route("flow-view")
public class FlowView extends VerticalLayout {

    private final DataPersistence dataPersistence;

    @Autowired
    public FlowView(DataPersistence dataPersistence) {
        this.dataPersistence = dataPersistence;

        Dashboard dashboard = new Dashboard();
        dashboard.setSizeFull();
        dashboard.setEditable(true);
        add(dashboard);

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

        Function<CustomWidget, SerializableDashboardItem> widgetToSerializableItem = widget -> {
            SerializableDashboardItem serializableDashboardWidget = new SerializableDashboardItem();
            serializableDashboardWidget.setTitle(widget.getTitle());
            serializableDashboardWidget.setColspan(widget.getColspan());
            serializableDashboardWidget.setRowspan(widget.getRowspan());
            serializableDashboardWidget.setWidgetId(widget.getWidgetId());
            serializableDashboardWidget.setImportantData(widget.getImportantData());
            serializableDashboardWidget.setWidgetType(widget.getWidgetType());
            return serializableDashboardWidget;
        };

        Function<Component, SerializableDashboardItem> itemToSerializableItem = item -> {
            SerializableDashboardItem serializableDashboardItem;
            if (item instanceof DashboardSection dashboardSection) {
                List<SerializableDashboardItem> serializableWidgets = dashboardSection.getWidgets().stream()
                        .map(CustomWidget.class::cast)
                        .map(widgetToSerializableItem)
                        .collect(Collectors.toCollection(ArrayList::new));
                serializableDashboardItem =  new SerializableDashboardItem();
                serializableDashboardItem.setTitle(dashboardSection.getTitle());
                serializableDashboardItem.setItems(serializableWidgets);
            } else {
                serializableDashboardItem = widgetToSerializableItem.apply((CustomWidget) item);
            }
            return serializableDashboardItem;
        };

        List<DashboardWidget> widgets = dashboard.getWidgets();
        CustomWidget widgetWithTextField = (CustomWidget) widgets.get(widgets.size() - 1);
        widgetWithTextField.addImportantDataChangeListener(e -> {
            List<SerializableDashboardItem> serializableDashboardItems = dashboard.getChildren().map(itemToSerializableItem).toList();
            dataPersistence.storeItems(serializableDashboardItems);
        });

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

            List<SerializableDashboardItem> serializableDashboardItems = dashboard.getChildren().map(itemToSerializableItem).toList();
            dataPersistence.storeItems(serializableDashboardItems);
        });
        add(moveWidgetOutOfSection);

        NativeButton removeFirstWidget = new NativeButton("Remove the first widget");
        removeFirstWidget.addClickListener(click -> {
            DashboardWidget widgetToRemove =  dashboard.getWidgets().get(0);
            dashboard.remove(widgetToRemove);
        });
        add(removeFirstWidget);
    }
}

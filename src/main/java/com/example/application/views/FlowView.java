package com.example.application.views;

import com.example.application.services.DataPersistence;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * *******************************************
 * TASK 1: Basic Setup and Initialization
 * *******************************************
 *
 * -1: Add the dashboard to the sample application.
 * -2: Set up two widgets and a section with two widgets.
 * -3: Set title and content to the items. You can use
 *      the provided charts in "PredefinedCharts" or any other
 *      custom components to create widgets.
 * -4: Place a span with text next to the title of a widget.
 * -5: Use the data from the method "DataPersistence.getItems"
 *      to populate the dashboard.
 * -6: Preserve the value of the text field in the widget
 *      whenever it is updated. You can use the provided
 *      "DataPersistence.updateImportantDataServer" method.
 *
 * *******************************************
 * TASK 2: Resizing Widgets
 * *******************************************
 *
 * -1: Define varying initial widget sizes.
 * -2: Resize a widget using a mouse.
 * -3: Resize a widget using the keyboard.
 * -4: Make sure that the height of the first widget
 *      cannot be changed.
 *
 * *******************************************
 * TASK 3: Moving Items
 * *******************************************
 *
 * -1: Move a widget anywhere within the dashboard with a mouse.
 * -2: Move a widget in the section using the keyboard.
 * -3: Move the section.
 * -4: Move a widget out of the section.
 * -5: Preserve the current layout of the widgets within the
 *      dashboard in the DB. You can use the provided
 *      “DataPersistence.storeItemsInDB” method.
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
 * -1: Remove a widget programmatically.
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
        // Write your code here
    }
}

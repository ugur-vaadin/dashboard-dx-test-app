package com.example.application.views.flowview;

import com.example.application.services.ChartsService;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * Task 1: Basic Setup and Initialization
 *   - Add the dashboard to the sample application.
 *   - Set up two widgets and a section with two widgets.
 *       You can use the provided charts or any other custom
 *       components to create widgets.
 *   - Set title and content to the items.
 *   - Set a custom header to a widget.
 *
 * Task 2: Resizing Widgets
 *   - Define varying initial widget dimensions.
 *   - Resize a widget using a mouse.
 *   - Resize a widget using the keyboard.
 *   - Make it so that the new dimensions of the widget gets
 *       updated in the DB whenever it is resized. You can
 *       use the provided “DataPersistence.updateItemInDB” method.
 *
 * Task 3: Moving Items
 *   - Move a widget anywhere within the dashboard with a mouse.
 *   - Move a widget in the section using the keyboard.
 *   - Move the section.
 *   - Move a widget out of the section.
 *   - Make a notification with the item title appear when
 *       an item is moved.
 *   - Make it so that the new order of the items gets updated
 *       in the DB whenever an item is moved. You can use
 *       the provided “DataPersistence.storeItemsInDB” method.
 *
 * Task 4: Automatically Filling Empty Space
 *   - Place the section after the first widget.
 *   - Make the dashboard automatically fill in the empty space
 *       with suitable widgets.
 *
 * Task 5: Removing Items
 *   - Remove a widget programmatically.
 *   - Remove a widget using the browser.
 *   - Remove a section using the browser.
 *   - Make a notification with the item title appear when an item
 *       is removed.
 *
 * Task 6: Layout and Styling
 *   - Remove the gap between the widgets.
 *   - Limit the maximum number of widgets in the same row to 2.
 *   - Make it so that the widgets in the dashboard are at least
 *       400px high and wide.
 *   - Change the color of the remove button to red.
 */
@PageTitle("Flow View")
@Menu(icon = "line-awesome/svg/globe-solid.svg", order = 0)
@Route("")
public class FlowView extends HorizontalLayout {

    public FlowView() {
        // Write your code here
    }

    private List<Chart> getCharts() {
        return ChartsService.getCharts();
    }
}

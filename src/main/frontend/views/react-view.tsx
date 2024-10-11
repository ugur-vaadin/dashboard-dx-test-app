import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import '@vaadin/vaadin-charts';
import { Chart, ChartSeries, } from '@vaadin/react-components-pro';
import '@vaadin/vaadin-lumo-styles/all-imports';

export const config: ViewConfig = { menu: { order: 1, icon: 'line-awesome/svg/globe-solid.svg' }, title: 'React View' };

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
 *       updated in the DB whenever it is resized. You can use the provided
 *       client callable “DataPersistence.updateItemInDB” method.
 *
 * Task 3: Moving Items
 *   - Move a widget anywhere within the dashboard with a mouse.
 *   - Move a widget in the section using the keyboard.
 *   - Move the section.
 *   - Move a widget out of the section.
 *   - Make a notification with the item title appear when
 *       an item is moved.
 *   - Make it so that the new order of the items gets updated
 *       in the DB whenever an item is moved. You can use the provided
 *       client callable “DataPersistence.storeItemsInDB” method.
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
function ReactView() {

  const ColumnChart = () => {
    return (
      <Chart type="column">
        <ChartSeries
          title="Sales"
          values={[100, 200, 300, 400, 500]}
        ></ChartSeries>
      </Chart>
    );
  };

  const LineChart = () => {
    return (
      <Chart type="line" title="Monthly Sales">
        <ChartSeries
          title="2023 Sales"
          values={[150, 250, 300, 450, 600]}
        ></ChartSeries>
        <ChartSeries
          title="2024 Sales"
          values={[200, 300, 350, 500, 650]}
        ></ChartSeries>
      </Chart>
    );
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
      </section>
    </>
  );
}

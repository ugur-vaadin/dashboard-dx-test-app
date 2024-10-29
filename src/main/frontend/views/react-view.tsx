import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import {
  Chart,
  ChartSeries,
  Dashboard,
  DashboardItem,
  DashboardSection,
  DashboardWidget
} from '@vaadin/react-components-pro';
import '@vaadin/vaadin-lumo-styles/all-imports';
import { DataPersistence } from 'Frontend/generated/endpoints.js';
import React from "react";
import {TextField} from "@vaadin/react-components";

export const config: ViewConfig = { menu: { order: 0, icon: 'line-awesome/svg/globe-solid.svg' }, title: 'React View' };

/*
 * *******************************************
 * TASK 1: Basic Setup and Initialization
 * *******************************************
 *
 * -1: Add the dashboard to the sample application.
 * -2: Set up two widgets and a section with two widgets.
 * -3: Set title and content to the items. You can use
 *      the provided widgets or use other components to
 *      create your custom widgets.
 * -4: Place a span with text next to the title of a widget.
 * -5: Add the predefined ImportantDataWidget to the dashboard.
 *      Preserve the value of the text field in the widget
 *      whenever it is updated. You can use the provided
 *      "DataPersistence.updateImportantDataClient" method.
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
 *      “DataPersistence.storeJsonItems” method. You can also use
 *      “DataPersistence.getJsonItems” method to retrieve the items.
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
function ReactView() {

  type TestItem = DashboardItem & {
    id?: number;
    title?: string;
    importantData?: string;
    type?: string;
  };

  const LineChartWidget = ({ item }: { item: TestItem }) => {
    return <DashboardWidget widgetTitle={item.title}>
      <Chart type="line" title="Monthly Sales">
        <ChartSeries title="2023 Sales" values={[150, 250, 300, 450, 600]}></ChartSeries>
        <ChartSeries title="2024 Sales" values={[200, 300, 350, 500, 650]}></ChartSeries>
      </Chart>
    </DashboardWidget>
  }

  const ColumnChartWidget = ({ item }: { item: TestItem }) => {
    return <DashboardWidget widgetTitle={item.title}>
      <Chart type="column">
        <ChartSeries title="Sales" values={[100, 200, 300, 400, 500]}></ChartSeries>
      </Chart>
    </DashboardWidget>
  }

  const ImportantDataWidget = ({ item }: { item: TestItem }) => {
    return <DashboardWidget widgetTitle={item.title}>
      <TextField
        title='Important data'
        value={item.importantData}
      />
    </DashboardWidget>
  }

  // TODO write your code here
  return (
    <>
      <section className="flex p-m gap-m items-end">
      </section>
    </>
  );
}

export default ReactView;

import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import {
  Chart,
  ChartSeries,
  Dashboard,
  DashboardItem,
  DashboardProps,
  DashboardSection,
  DashboardSectionItem,
  DashboardWidget
} from '@vaadin/react-components-pro';
import '@vaadin/vaadin-lumo-styles/all-imports';
import { DataPersistence } from 'Frontend/generated/endpoints.js';
import React, { useEffect, useState } from "react";
import { Button, TextField } from "@vaadin/react-components";
import { getPredefinedChart } from "Frontend/utils/predefined-charts";
import SerializableDashboardItem from "Frontend/generated/com/example/application/services/SerializableDashboardItem";

export const config: ViewConfig = { menu: { order: 0, icon: 'line-awesome/svg/globe-solid.svg' }, title: 'React View' };

/*
 * *******************************************
 * TASK 1: Basic Setup and Initialization
 * *******************************************
 *
 * -1: Add a dashboard with two widgets and a section with two
 *      widgets to the sample application. The widgets and the
 *      section should have titles.
 * -2: Set title and content to the components. You can use the
 *      provided charts in “PredefinedCharts” or any other
 *      custom components to create widgets.
 * -3: Place a span with text next to the title of a widget.
 * -4: Use the data from the method "DataPersistence.getItems"
 *      to populate the dashboard.
 * -5: Add a text field that displays the important data to the
 *      widgets of items without widget types.
 * -6: Preserve the current layout of the widgets within the
 *      dashboard whenever the value of the text field in the
 *      widget is updated. You can use the provided
 *      “DataPersistence.storeItems” method.
 *
 * *******************************************
 * TASK 2: Resizing Widgets
 * *******************************************
 *
 * -1: Add a button that makes the first widget consume 2 rows
 *      and 2 columns in the dashboard's layout.
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
 * -1: Place the sections so that there is space left next to
 *      the first widget. Make the dashboard automatically fill
 *      in the empty space with suitable widgets.
 *
 * *******************************************
 * TASK 5: Removing Items
 * *******************************************
 *
 * -1: Remove a widget programmatically.
 * -2: Remove a widget using the UI.
 * -3: Remove a section using the UI.
 * -4: Preserve the current layout of the widgets within the
 *      dashboard whenever an item is removed.
 *
 * *******************************************
 * TASK 6: Layout and Styling
 * *******************************************
 *
 * -1: Remove the gap between the widgets.
 * -2: Limit the maximum number of widgets in the same row to 2.
 * -3: Make it so that the widgets in the dashboard are at least
 *       200px in height and width.
 * -4: Change the color of the remove button to red.
 */
function ReactView() {

  type TestItem = Awaited<ReturnType<typeof DataPersistence.getItems>>[0];

  // TODO write your code here
  return (
    <>
    </>
  );
}

export default ReactView;

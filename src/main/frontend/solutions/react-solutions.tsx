import {
  Dashboard,
  DashboardWidget,
  DashboardItem,
  DashboardProps,
  DashboardSectionItem,
} from '@vaadin/react-components-pro';
import { Button, TextField } from '@vaadin/react-components';
import { DataPersistence } from 'Frontend/generated/endpoints.js';
import React, { useEffect, useState } from "react";
import { getPredefinedChart } from "Frontend/utils/predefined-charts";
import SerializableDashboardItem from "Frontend/generated/com/example/application/services/SerializableDashboardItem";

type TestItem = Awaited<ReturnType<typeof DataPersistence.getItems>>[0];

/*
 * *******************************************
 * TASK 1: Basic Setup and Initialization
 * *******************************************
 */

// Subtask 1.1: Add a dashboard with two widgets and a section
//      with two widgets to the sample application. The widgets
//      and the section should have titles.
let items: DashboardProps<TestItem>['items'] =
  [
    { title: 'Widget 1' },
    { title: 'Widget 2' },
    {
      title: 'Section',
      items: [
        { title: 'Widget in section 1' },
        { title: 'Widget in section 2' },
      ],
    },
  ];

let dashboard = () => {
  return (
    <Dashboard items={items}>
      {({ item }) => (
        <DashboardWidget widgetTitle={item.title}/>
      )}
    </Dashboard>
  )
};

/*
 * Subtask 1.2: Set content to the components. You can use the
 *      provided charts in “PredefinedCharts” or any other
 *      custom components to create widgets.
 */
items =
  [
    {
      title: 'Widget 1',
      widgetType: 'AREA_CHART',
    },
    {
      title: 'Widget 2',
      widgetType: 'AREA_SPLINE_CHART',
    },
    {
      title: 'Section',
      items: [
        {
          title: 'Widget in section 1',
          widgetType: 'BAR_CHART',
        },
        {
          title: 'Widget in section 2',
          widgetType: 'COLUMN_CHART',
        },
      ],
    },
  ];

let renderer = ({ item }: { item: TestItem }) => {
  return <DashboardWidget widgetTitle={item.title}>
    {item.widgetType ? getPredefinedChart(item.widgetType) : ''}
  </DashboardWidget>;
};

dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={items}
    >{renderer}</Dashboard>
  )
};

// Subtask 1.3: Place a span with text next to the title of a widget.
renderer = ({ item }: { item: TestItem }) => {
  return <DashboardWidget widgetTitle={item.title}>
    <span slot="header-content">Some Header Content</span>
    {item.widgetType ? getPredefinedChart(item.widgetType) : ''}
  </DashboardWidget>;
};

/*
 * Subtask 1.4: Use the data from the method
 *      "DataPersistence.getItems" to populate the dashboard.
 */
let setItems;
[items, setItems] = useState<(TestItem | DashboardSectionItem<TestItem>)[]>([]);

useEffect(() => {
  DataPersistence.getItems().then(r => setItems(r));
}, []);

/*
 * Subtask 1.5: Add a text field that displays the important
 *      data to the widgets of items without widget types.
 */
renderer = ({ item }: { item: TestItem }) => {
  return <DashboardWidget widgetTitle={item.title}>
    <span slot="header-content">Some Header Content</span>
    { item.widgetType ? getPredefinedChart(item.widgetType) :
      <TextField
        title='Important data'
        value={item.importantData}
      />
    }
  </DashboardWidget>;
};

/*
 * Subtask 1.6: Preserve the current layout of the widgets within the
 *      dashboard whenever the value of the text field in the widget
 *      is updated. You can use the provided “DataPersistence.storeItems”
 *      method.
 */
const storeItems = (items: (TestItem | DashboardSectionItem<TestItem>)[]) => {
  DataPersistence.storeItems(items as SerializableDashboardItem[]);
}

renderer = ({ item }: { item: TestItem }) => {
  return <DashboardWidget widgetTitle={item.title}>
    <span slot="header-content">Some Header Content</span>
    { item.widgetType ? getPredefinedChart(item.widgetType) :
      <TextField
        title='Important data'
        value={item.importantData}
        onChange={(e) => {
          item.importantData = e.target.value;
          storeItems(items);
        }}
      />
    }
  </DashboardWidget>;
};

/*
 * *******************************************
 * TASK 2: Resizing Widgets
 * *******************************************
 */

// Subtask 2.1: Add a button that makes the first widget consume 2 rows
//      and 2 columns in the dashboard's layout.
const updateFirstWidgetSize = () => {
  return <Button onClick={() => {
    // @ts-ignore
    const newItems = [...items];
    const firstItem = newItems[0] as TestItem;
    firstItem.rowspan = 2;
    firstItem.colspan = 2;
    setItems(newItems);
  }}
  >Update first item size</Button>;
}

dashboard = () => {
  return (
    <Dashboard items={items} style={{
      '--vaadin-dashboard-row-min-height': '250px',
    }}>
      {renderer}
    </Dashboard>
  );
};

// Subtask 2.2: Resize a widget using a mouse. Dashboard should be
//      configured for that purpose.
dashboard = () => {
  return (
    <Dashboard items={items} editable style={{
      '--vaadin-dashboard-row-min-height': '250px',
    }}>
      {renderer}
    </Dashboard>
  );
};

// Resizing is done on the UI

// Subtask 2.3: Resize a widget using the keyboard.
// Resizing is done on the UI

// Subtask 2.4: Preserve the current layout of the widgets within the
//      dashboard whenever a widget is resized.
dashboard = () => {
  return (
    <Dashboard
      items={items}
      editable
      style={{
        '--vaadin-dashboard-row-min-height': '250px',
      }}
      onDashboardItemResized={(e) => storeItems(e.detail.items)}
    >
      {renderer}
    </Dashboard>
  );
};

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
const moveWidgetOutOfSection = () => {
  return <Button onClick={() => {
    const newItems = [...items];
    const sectionItem = newItems.filter(item => item.items)[0];
    const widgetInSection = sectionItem.items?.pop();
    newItems.push(widgetInSection as TestItem);
    setItems(newItems);
  }}
  >Move a widget out of the section</Button>;
}

/*
 * Subtask 3.5: Preserve the current layout of the widgets within the
 *      dashboard whenever an item is moved.
 */
dashboard = () => {
  return (
    <Dashboard
      items={items}
      editable
      style={{
        '--vaadin-dashboard-row-min-height': '250px',
      }}
      onDashboardItemResized={(e) => storeItems(e.detail.items)}
      onDashboardItemMoved={(e) => storeItems(e.detail.items)}
    >
      {renderer}
    </Dashboard>
  );
};

/*
 * *******************************************
 * TASK 4: Automatically Filling Empty Space
 * *******************************************
 */

/*
 * Subtask 4.1: Place the sections so that there is space left next to the
 *      first widget. Make the dashboard automatically fill in the empty
 *      space with suitable widgets.
 */
// Moving is done on the UI
dashboard = () => {
  return (
    <Dashboard
      items={items}
      editable
      denseLayout
      style={{
        '--vaadin-dashboard-row-min-height': '250px',
      }}
      onDashboardItemResized={(e) => storeItems(e.detail.items)}
      onDashboardItemMoved={(e) => storeItems(e.detail.items)}
    >
      {renderer}
    </Dashboard>
  );
};

/**
 * *******************************************
 * TASK 5: Removing Items
 * *******************************************
 */

// Subtask 5.1: Remove the first widget on a button click.
const removeFirstWidget = () => {
  return <Button onClick={() => {
    const newItems = [...items];
    newItems.shift();
    setItems(newItems);
  }}
  >Remove the first widget</Button>;
}

// Subtask 5.2: Remove a widget using the UI.
// Removing is done on the UI

// Subtask 5.3: Remove a section using the UI.
// Removing is done on the UI

/*
 * Subtask 5.4: Preserve the current layout of the widgets within the
 *      dashboard whenever an item is removed.
 */
dashboard = () => {
  return (
    <Dashboard
      items={items}
      editable
      denseLayout
      style={{
        '--vaadin-dashboard-row-min-height': '250px',
      }}
      onDashboardItemResized={(e) => storeItems(e.detail.items)}
      onDashboardItemMoved={(e) => storeItems(e.detail.items)}
      onDashboardItemRemoved={(e) => storeItems(e.detail.items)}
    >
      {renderer}
    </Dashboard>
  );
};

/**
 * *******************************************
 * TASK 6: Layout and Styling
 * *******************************************
 */

// Subtask 6.1: Remove the gap between the widgets.
dashboard = () => {
  return (
    <Dashboard
      items={items}
      editable
      denseLayout
      style={{
        '--vaadin-dashboard-row-min-height': '250px',
        '--vaadin-dashboard-spacing': '0px'
      }}
      onDashboardItemResized={(e) => storeItems(e.detail.items)}
      onDashboardItemMoved={(e) => storeItems(e.detail.items)}
      onDashboardItemRemoved={(e) => storeItems(e.detail.items)}
    >
      {renderer}
    </Dashboard>
  );
};

// Subtask 6.2: Limit the maximum number of widgets in the same row to 2.
dashboard = () => {
  return (
    <Dashboard
      items={items}
      editable
      denseLayout
      style={{
        '--vaadin-dashboard-row-min-height': '250px',
        '--vaadin-dashboard-spacing': '0px',
        '--vaadin-dashboard-col-max-count': '2'
      }}
      onDashboardItemResized={(e) => storeItems(e.detail.items)}
      onDashboardItemMoved={(e) => storeItems(e.detail.items)}
      onDashboardItemRemoved={(e) => storeItems(e.detail.items)}
    >
      {renderer}
    </Dashboard>
  );
};

/*
 * Subtask 6.3: Make it so that the widgets in the dashboard are at least
 *       200px in height and width.
 */
dashboard = () => {
  return (
    <Dashboard
      items={items}
      editable
      denseLayout
      style={{
        '--vaadin-dashboard-spacing': '0px',
        '--vaadin-dashboard-col-max-count': '2',
        '--vaadin-dashboard-col-min-width': '200px',
        '--vaadin-dashboard-row-min-height': '200px'
      }}
      onDashboardItemResized={(e) => storeItems(e.detail.items)}
      onDashboardItemMoved={(e) => storeItems(e.detail.items)}
      onDashboardItemRemoved={(e) => storeItems(e.detail.items)}
    >
      {renderer}
    </Dashboard>
  );
};


// Subtask 6.4: Change the color of the remove button to red.
dashboard = () => {
  return (
    <div>
      <style>{`
        vaadin-dashboard-widget::part(remove-button) {
          color: red;
        }
        
        vaadin-dashboard {
          --vaadin-dashboard-spacing: 0px;
          --vaadin-dashboard-col-max-count: 2;
          --vaadin-dashboard-col-min-width: 200px;
          --vaadin-dashboard-row-min-height: 200px;
        }
      `}
      </style>
      <Dashboard
        items={items}
        editable
        denseLayout
        onDashboardItemResized={(e) => storeItems(e.detail.items)}
        onDashboardItemMoved={(e) => storeItems(e.detail.items)}
        onDashboardItemRemoved={(e) => storeItems(e.detail.items)}
      >
        {renderer}
      </Dashboard>
    </div>
  );
};

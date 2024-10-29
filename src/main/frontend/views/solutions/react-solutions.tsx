import {
  Dashboard,
  DashboardWidget,
  Chart,
  ChartSeries, DashboardItem, DashboardProps, DashboardSectionItem,
} from '@vaadin/react-components-pro';
import { Button, TextField } from '@vaadin/react-components';
import { DataPersistence } from 'Frontend/generated/endpoints.js';
import React, {useEffect, useState} from "react";

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

/*
 * *******************************************
 * TASK 1: Basic Setup and Initialization
 * *******************************************
 */

// Subtask 1.1: Add the dashboard to the sample application.
const task_1_1_dashboard = () => {
  return (
    <Dashboard />
  )
};

// Subtask 1.2: Set up two widgets and a section with two widgets.
const task_1_2_items: DashboardProps<DashboardItem>['items'] =
  [
    { },
    { },
    {
      items: [
        { },
        { }
      ]
    },
  ];

const task_1_2_dashboard = () => {
  return (
    <Dashboard items={task_1_2_items}/>
  )
};

/*
 * Subtask 1.3: Set title and content to the items. You can use
 *      the provided widgets or use other components to create
 *      your custom widgets.
 */
const task_1_3_items: DashboardProps<TestItem>['items'] =
  [
    {
      id: 0,
      title: 'Widget 1',
      type: 'column',
    },
    {
      id: 1,
      title: 'Widget 2',
      type: 'line',
    },
    {
      id: 2,
      title: 'Section',
      items: [
        {
          id: 3,
          title: 'Widget in section 1',
          type: 'column',
        },
        {
          id: 4,
          title: 'Widget in section 2',
          type: 'line',
        },
      ],
    },
  ];

const task_1_3_renderer = ({ item }: { item: TestItem }) => {
  switch (item.type) {
    case 'line':
      return <LineChartWidget item={item} />;
    case 'column':
      return <ColumnChartWidget item={item} />;
    case 'text':
      return <ImportantDataWidget item={item} />;
    default:
      return null;
  }
};

const task_1_3_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_1_3_items}
    >{task_1_3_renderer}</Dashboard>
  )
};

// Subtask 1.4: Place a span with text next to the title of a widget.
const Task1_4_columnChartWidget = ({ item }: { item: TestItem }) => {
  return <DashboardWidget widgetTitle={item.title}>
    <span slot="header-content">Some Header Content</span>
    <Chart type="column">
      <ChartSeries title="Sales" values={[100, 200, 300, 400, 500]}></ChartSeries>
    </Chart>
  </DashboardWidget>
}

const task_1_4_renderer = ({ item }: { item: TestItem }) => {
  switch (item.type) {
    case 'line':
      return <LineChartWidget item={item} />;
    case 'column':
      return <Task1_4_columnChartWidget item={item} />;
    case 'text':
      return <ImportantDataWidget item={item} />;
    default:
      return null;
  }
};

const task_1_4_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_1_3_items}
    >{task_1_4_renderer}</Dashboard>
  );
};

/*
 * Subtask 1.5: Add the predefined ImportantDataWidget to the dashboard.
 *      Preserve the value of the text field in the widget
 *      whenever it is updated. You can use the provided
 *      "DataPersistence.updateImportantData" method.
 */
const task_1_5_items: DashboardProps<TestItem>['items'] =
  [
    {
      id: 0,
      title: 'Widget 1',
      type: 'column',
    },
    {
      id: 1,
      title: 'Widget 2',
      type: 'line',
    },
    {
      id: 2,
      title: 'Section',
      items: [
        {
          id: 3,
          title: 'Widget in section 1',
          type: 'column',
        },
        {
          id: 4,
          title: 'Widget in section 2',
          type: 'line',
        },
      ],
    },
    {
      id: 5,
      title: 'Widget with text field',
      type: 'text',
    },
  ];

const Task_1_5_importantDataWidget = ({ item }: { item: TestItem }) => {
  return <DashboardWidget widgetTitle={item.title}>
    <TextField
      title='Important data'
      value={item.importantData}
      onChange={(e) => DataPersistence.updateImportantData(item.id as number, e.target.value)}
    />
  </DashboardWidget>
}

const task_1_5_renderer = ({ item }: { item: TestItem }) => {
  switch (item.type) {
    case 'line':
      return <LineChartWidget item={item} />;
    case 'column':
      return <ColumnChartWidget item={item} />;
    case 'text':
      return <Task_1_5_importantDataWidget item={item} />;
    default:
      return null;
  }
};

const task_1_5_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_1_5_items}
    >{task_1_5_renderer}</Dashboard>
  );
};

/*
 * *******************************************
 * TASK 2: Resizing Widgets
 * *******************************************
 */

// Subtask 2.1: Define varying initial widget sizes.
const task_2_1_items: DashboardProps<TestItem>['items'] = [
  { id: 0, title: 'Widget 1', type: 'column', rowspan: 2 },
  { id: 1, title: 'Widget 2', type: 'line', colspan: 2 },
];


const task_2_1_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_2_1_items}
    >{task_1_5_renderer}</Dashboard>
  );
};


// Subtask 2.2: Resize a widget using a mouse.
const task_2_2_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_2_1_items}
      editable
    >{task_1_5_renderer}</Dashboard>
  );
};

// Resizing is done on the UI

// Subtask 2.3: Resize a widget using the keyboard.
// Resizing is done on the UI

// Subtask 2.4: Make sure the height of the first widget cannot be changed.
const task_2_4_handleWidgetResize = (event: any) => {
  const resizedItem = event.detail.item;
  if (resizedItem.title === 'Widget 1') {
    resizedItem.rowspan = 2;
  }
};

const task_2_4_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_2_1_items}
      editable
      onDashboardItemResized={task_2_4_handleWidgetResize}
    >{task_1_5_renderer}</Dashboard>
  );
};

/*
 * *******************************************
 * TASK 3: Moving Items
 * *******************************************
 */

const task_3_items: DashboardProps<TestItem>['items'] =
  [
    {
      id: 0,
      title: 'Widget 1',
      type: 'column',
    },
    {
      id: 1,
      title: 'Widget 2',
      type: 'line',
    },
    {
      id: 2,
      title: 'Section',
      items: [
        {
          id: 3,
          title: 'Widget in section 1',
          type: 'column',
        },
        {
          id: 4,
          title: 'Widget in section 2',
          type: 'line',
        },
      ],
    },
  ];

const task_3_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_3_items}
      editable
    >{task_1_5_renderer}</Dashboard>
  );
};

// Subtask 3.1: Move a widget anywhere within the dashboard with a mouse.
// Moving is done on the UI

// Subtask 3.2: Move a widget in the section using the keyboard.
// Moving is done on the UI

// Subtask 3.3: Move the section.
// Moving is done on the UI

// Subtask 3.4: Move a widget out of the section.
// Moving cannot be done on the UI
const task_3_4_items: DashboardProps<TestItem>['items'] =
  [
    {
      title: 'Widget 1',
      type: 'column',
    },
    {
      title: 'Widget in section 1',
      type: 'column',
    },
    {
      title: 'Widget 2',
      type: 'line',
    },
    {
      title: 'Section',
      items: [
        {
          title: 'Widget in section 2',
          type: 'line',
        },
      ],
    },
  ];

const task_3_4_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_3_4_items}
      editable
    >{task_1_5_renderer}</Dashboard>
  );
};

/*
 * Subtask 3.5: Preserve the current layout of the widgets within
 *      the dashboard in the DB. You can use the provided
 *      “DataPersistence.storeJsonItems” method. You can also use
 *      “DataPersistence.getJsonItems” method to retrieve the items.
 */
const [items, setItems] = useState<(TestItem | DashboardSectionItem<TestItem>)[]>([]);

const handleSave = async () => {
  await DataPersistence.storeJsonItems(JSON.stringify(items));
};

const task_3_5_dashboard = () => {
  return (
    <div>
      <Button onClick={handleSave}>Save Layout to DB</Button>
      <Dashboard
        items={items}
        editable
        onDashboardItemMoved={(e: any) => setItems(e.detail.items)}
        onDashboardItemRemoved={(e: any) => setItems(e.detail.items)}
        onDashboardItemResized={(e: any) => setItems(e.detail.items)}
      >{task_1_5_renderer}</Dashboard>
    </div>
  );
};

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
const task_4_1_items: DashboardProps<TestItem>['items'] =
  [
    {
      title: 'Widget 1',
      type: 'column',
    },
    {
      title: 'Section',
      items: [
        {
          title: 'Widget in section 2',
          type: 'line',
        },
      ],
    },
    {
      title: 'Widget 2',
      type: 'line',
    },
  ];

const task_4_1_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_4_1_items}
      editable
      dense-layout
    >{task_1_5_renderer}</Dashboard>
  );
};

/**
 * *******************************************
 * TASK 5: Removing Items
 * *******************************************
 */

// Subtask 5.1: Remove a widget programmatically.
const task_5_1_items: DashboardProps<TestItem>['items'] =
  [
    {
      title: 'Widget 2',
      type: 'line',
    },
    {
      title: 'Section',
      items: [
        {
          title: 'Widget in section 1',
          type: 'column',
        },
        {
          title: 'Widget in section 2',
          type: 'line',
        },
      ],
    },
  ];

const task_5_1_dashboard = () => {
  return (
    <Dashboard<TestItem>
      items={task_5_1_items}
      editable
    >{task_1_5_renderer}</Dashboard>
  );
};

// Subtask 5.2: Remove a widget using the UI.
// Removing is done on the UI

// Subtask 5.3: Remove a section using the UI.
// Removing is done on the UI

/**
 * *******************************************
 * TASK 6: Layout and Styling
 * *******************************************
 */

// Subtask 6.1: Remove the gap between the widgets.
const task_6_1_dashboard = () => {
  return (
    <Dashboard<TestItem>
      style={{ '--vaadin-dashboard-spacing': '0px' }}
      items={task_5_1_items}
      editable
    >{task_1_5_renderer}</Dashboard>
  );
};

// Subtask 6.2: Limit the maximum number of widgets in the same row to 2.
const task_6_2_dashboard = () => {
  return (
    <Dashboard<TestItem>
      style={{ '--vaadin-dashboard-col-max-count': '2' }}
      items={task_5_1_items}
      editable
    >{task_1_5_renderer}</Dashboard>
  );
};

/*
 * Subtask 6.3: Make it so that the widgets in the dashboard are at least
 *       400px in height and width.
 */
const task_6_3_dashboard = () => {
  return (
    <Dashboard<TestItem>
      style={{
        '--vaadin-dashboard-col-min-width': '400px',
        '--vaadin-dashboard-col-min-height': '400px'
      }}
      items={task_5_1_items}
      editable
    >{task_1_5_renderer}</Dashboard>
  );
};


// Subtask 6.4: Change the color of the remove button to red.
const task_6_4_dashboardStyle = `
    vaadin-dashboard-widget::part(remove-button) {
      color: red;
    }
  `;

const task_6_4_dashboard = () => {
  return (
    <div>
      <style>{task_6_4_dashboardStyle}</style>
      <Dashboard
        items={task_5_1_items}
        editable
      >{task_1_5_renderer}</Dashboard>
    </div>
  );
};

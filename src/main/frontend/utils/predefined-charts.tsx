import {
  Chart,
  ChartSeries,
} from '@vaadin/react-components-pro';
import '@vaadin/vaadin-lumo-styles/all-imports';
import React from "react";

const BaseChart = (chartType: string) => {
  return () => (
    <Chart
      type={chartType}
    >
      <ChartSeries title="2023" values={[150, 250, 300, 450, 600]} />
      <ChartSeries title="2024" values={[200, 300, 350, 500, 650]} />
    </Chart>
  );
};

export const AreaChart = BaseChart('area');
export const AreaSplineChart = BaseChart('areaspline');
export const BarChart = BaseChart('bar');
export const ColumnChart = BaseChart('column');
export const PieChart = BaseChart('pie');
export const ScatterChart = BaseChart('scatter');

export const getPredefinedChart = (type: string) => {
  switch (type) {
    case 'AREA_CHART':
      return <AreaChart />;
    case 'AREA_SPLINE_CHART':
      return <AreaSplineChart />;
    case 'BAR_CHART':
      return <BarChart />;
    case 'COLUMN_CHART':
      return <ColumnChart />;
    case 'PIE_CHART':
      return <PieChart />;
    case 'SCATTER_CHART':
      return <ScatterChart />;
    default:
      return null;
  }
}
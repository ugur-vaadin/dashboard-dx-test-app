import {
  Chart,
  ChartSeries,
} from '@vaadin/react-components-pro';
import '@vaadin/vaadin-lumo-styles/all-imports';
import React from "react";

interface BaseChartProps {
  type: string;
}

const BaseChart: React.FC<BaseChartProps> = ({ type }) => (
  <Chart type={type}>
    <ChartSeries title="2023" values={[150, 250, 300, 450, 600]} />
    <ChartSeries title="2024" values={[200, 300, 350, 500, 650]} />
  </Chart>
);

export const AreaChart: React.FC = () => <BaseChart type="area" />;
export const AreaSplineChart: React.FC = () => <BaseChart type="areaspline" />;
export const BarChart: React.FC = () => <BaseChart type="bar" />;
export const ColumnChart: React.FC = () => <BaseChart type="column" />;
export const PieChart: React.FC = () => <BaseChart type="pie" />;
export const ScatterChart: React.FC = () => <BaseChart type="scatter" />;

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
};

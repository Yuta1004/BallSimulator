package util;

import java.util.Collection;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.chart.XYChart;

import object.Pos;
import object.SimulatableObject;

public class Convert {

    /**
     * オブジェクト情報を受け取って対応するXYSeriesを返す
     * @param objests オブジェクト情報
     * @return XYChart.Series<Number, Number>
     */
    public static XYChart.Series<Number, Number> objects2Series(Collection<SimulatableObject> objects) {
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        for(SimulatableObject obj: objects) {
            Pos pos = obj.getPos();
            XYChart.Data<Number, Number> data = new XYChart.Data<Number, Number>(pos.getX(), pos.getY());
            Color color = Color.web("#000");
            data.setNode(new Circle(10.0, color));
            series.getData().add(data);
        }
        return series;
    }

}
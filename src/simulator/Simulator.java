package simulator;

import java.util.HashMap;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.chart.XYChart;

import object.Pos;
import object.SimulatableObject;

public class Simulator {

    // オブジェクト管理用
    private HashMap<String, Color> colorTable;
    private HashMap<String, SimulatableObject> objects;

    /* コンストラクタ */
    public Simulator() {
        colorTable = new HashMap<String, Color>();
        objects = new HashMap<String, SimulatableObject>();
    }

    /**
     * シミュレートを進める
     * @param time 進める時間 [10^-2s]
     */
    public void step(int time) {
        for(SimulatableObject obj: objects.values()) {
            obj.step(time);
        }
    }

    /* リセット */
    public void reset() {
        for(SimulatableObject obj: objects.values()) {
            obj.reset();
        }
    }

    /**
     * オブジェクト追加
     * @param id ID
     * @param color 色情報 (JavaFX.Colorで使用可能なもの ex.0xa52a2aff)
     * @param obj 追加オブジェクト
     */
    public void addObject(String id, String color, SimulatableObject obj) {
        objects.put(id, obj);
        colorTable.put(id, Color.valueOf(color));
    }

    /**
     * オブジェクト削除
     * @param id ID
     */
    public void removeObject(String id) {
        if(objects.get(id) == null) { return; }
        objects.remove(id);
        colorTable.remove(id);
    }

    /* オブジェクトリストを返す */
    public HashMap<String, SimulatableObject> getObjects() {
        return objects;
    }

    /* オブジェクト描画のためのXYChart.Seriesを返す */
    public XYChart.Series<Number, Number> getSeries() {
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        for(String id: objects.keySet()) {
            SimulatableObject obj = objects.get(id);
            Pos pos = obj.getPos();
            XYChart.Data<Number, Number> data = new XYChart.Data<Number, Number>(pos.getX(), pos.getY());
            data.setNode(new Circle(10.0, colorTable.get(id)));
            series.getData().add(data);
        }
        return series;
    }

}
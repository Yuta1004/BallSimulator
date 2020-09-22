package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import util.Clock;
import util.Parse;
import util.Convert;
import object.SimulatableObject;
import simulator.Simulator;

public class MainUIController implements Initializable {

    // UI部品
    @FXML
    private AnchorPane chartPane;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedVal, clockVal;
    @FXML
    private Button play, init, reset;
    @FXML
    private TextField widthF, widthT, heightF, heightT;

    // 描画用
    private ScatterChart<Number, Number> chart;
    private double updateSpeed;
    private double widthFVal, widthTVal, heightFVal, heightTVal;
    private Clock clock;
    private Simulator simulator;
    private Timeline tl = new Timeline();

    /* 初期化 */
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // 各種値初期化
        clock = new Clock(0, 0, 0, 0);
        updateSpeed = 1.0;
        widthTVal = 25;
        heightTVal = 10;
        widthFVal = heightFVal = 0;

        // UI部品など初期化
        initUI();
        initChart(false);

        // シミュレータ初期化
        simulator = new Simulator();
        plotData(simulator.getObjectList().values());
    }

    /* UI初期化 */
    private void initUI() {
        // UIイベント<ボタン>
        init.setOnAction(event -> {
            tl.stop();
            initChart(false);
            play.setText("▷");
            clock.set(0, 0, 0, 0);
            clockVal.setText(clock.toString());
            simulator = new Simulator();
        });
        reset.setOnAction(event -> {
            tl.stop();
            initChart(false);
            simulator.reset();
            play.setText("▷");
            clock.set(0, 0, 0, 0);
            clockVal.setText(clock.toString());
            plotData(simulator.getObjectList().values());
        });
        play.setOnAction(event -> {
            if(tl.getStatus().equals(Animation.Status.RUNNING)) {
                play.setText("▷");
                tl.stop();
            } else {
                play.setText("□");
                initTimeLine();
            }
        });

        // UIイベント<スライダー>
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateSpeed = Math.round(oldVal.doubleValue()*10)/10.0;
            speedVal.setText("x"+updateSpeed);
            if(tl.getStatus().equals(Animation.Status.RUNNING))
                initTimeLine();
        });

        // UIイベント<テキスト入力(1行)>
        widthF.textProperty().addListener((obs, oldText, newText) -> {
            widthFVal = Parse.toDouble(newText, 0.0);
            initChart(true);
        });
        widthT.textProperty().addListener((obs, oldText, newText) -> {
            widthTVal = Parse.toDouble(newText, 0.0);
            initChart(true);
        });
        heightF.textProperty().addListener((obs, oldText, newText) -> {
            heightFVal = Parse.toDouble(newText, 0.0);
            initChart(true);
        });
        heightT.textProperty().addListener((obs, oldText, newText) -> {
            heightTVal = Parse.toDouble(newText, 0.0);
            initChart(true);
        });
    }

    /* ScatterChart初期化 */
    private void initChart(boolean takeover) {
        // NumberAxis初期化
        // axisNormalize();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X(m)");
        xAxis.setAutoRanging(false);
        xAxis.setTickUnit((widthTVal-widthFVal)/20);
        xAxis.setLowerBound(widthFVal);
        xAxis.setUpperBound(widthTVal);
        yAxis.setLabel("Y(m)");
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit((heightTVal-heightFVal)/20);
        yAxis.setLowerBound(heightFVal);
        yAxis.setUpperBound(heightTVal);

        // データ保存
        ObservableList<XYChart.Series<Number, Number>> tmp = null;
        if(takeover && chart != null)
           tmp = chart.getData();

        // Chart設定
        chart = new ScatterChart<Number, Number>(xAxis, yAxis);
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        if(takeover)
            chart.setData(tmp);

        // 配置
        AnchorPane.setTopAnchor(chart, 0.0);
        AnchorPane.setLeftAnchor(chart, 0.0);
        AnchorPane.setRightAnchor(chart, 20.0);
        AnchorPane.setBottomAnchor(chart, 0.0);
        chartPane.getChildren().clear();
        chartPane.getChildren().add(chart);
    }

    /* TimeLine初期化 */
    private void initTimeLine() {
        Duration d = new Duration(500/updateSpeed);
        KeyFrame kf = new KeyFrame(d, event -> {
            clock.tick(10);     // 10ms
            simulator.step(1);  // 10ms
            clockVal.setText(clock.toString());
            plotData(simulator.getObjectList().values());
        });
        tl.stop();
        tl = new Timeline(kf);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    /* ScatterChartにデータをセットする */
    @SuppressWarnings("unchecked")
    private void plotData(Collection<SimulatableObject> objects) {
        XYChart.Series<Number, Number> series = Convert.objects2Series(objects);
        chart.getData().clear();
        chart.getData().addAll(series);
    }

}

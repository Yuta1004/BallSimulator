package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
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
import java.util.ResourceBundle;

import util.Clock;
import util.Parse;
import util.JavaFXStage;
import data.Settings;
import simulator.Simulator;

import object.Ball;  // for debug

public class MainUIController implements Initializable {

    // UI部品
    @FXML
    private AnchorPane chartPane;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedVal, clockVal;
    @FXML
    private Button play, skip, skip10, init, reset, addObj, openSettings;
    @FXML
    private TextField widthF, widthT, heightF, heightT;

    // 描画用
    private ScatterChart<Number, Number> chart;
    private double updateSpeed;
    private double widthFVal, widthTVal, heightFVal, heightTVal;
    private Clock clock;
    private Simulator simulator;
    private Timeline tl = new Timeline();

    // その他
    ResourceBundle resource;

    /* 初期化 */
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // 各種値初期化
        clock = new Clock(0, 0, 0, 0);
        updateSpeed = 1.0;
        widthTVal = 25;
        heightTVal = 10;
        widthFVal = heightFVal = 0;
        this.resource = resource;

        // UI部品など初期化
        initUI();
        initChart(false);

        // シミュレータ初期化
        simulator = new Simulator();
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
            plotData();
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
        skip.setOnAction(event -> {
            simulator.step(1);
            plotData();
            clock.tick(10);
            clockVal.setText(clock.toString());
        });
        skip10.setOnAction(event -> {
            simulator.step(10);
            plotData();
            clock.tick(100);
            clockVal.setText(clock.toString());
        });
        addObj.setOnAction(event -> {
            addObject();
        });
        openSettings.setOnAction(event -> {
            tl.stop();
            play.setText("▷");
            SettingsController controller = new SettingsController();
            Stage stage = JavaFXStage.genStage("Settings", "/fxml/Settings.fxml", controller, resource);
            stage.showAndWait();
            initChart(true);
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
        if(Settings.AxisNormalize)
            axisNormalize();
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

        // 拡大率セット
        double scaleX = 25.0/(widthTVal-widthFVal);
        double scaleY = 10.0/(heightTVal-heightFVal);
        if(chart != null) {
            ObservableList<XYChart.Series<Number, Number>> series = chart.getData();
            for(XYChart.Series<Number, Number> s: series)
                setChartScale(s, scaleX, scaleY);
        }

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
            clock.tick(Settings.StepVal);
            simulator.step(Settings.StepVal/10);
            clockVal.setText(clock.toString());
            plotData();
        });
        tl.stop();
        tl = new Timeline(kf);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    /* ScatterChartにデータをセットする */
    @SuppressWarnings("unchecked")
    private void plotData() {
        XYChart.Series<Number, Number> series = simulator.getSeries();
        chart.getData().clear();
        chart.getData().addAll(series);
    }

    /**
     * グラフ表示のスケーリングを行う
     *
     * @param series スケーリング対象となるデータ群
     * @param rateX X方向の拡大率
     * @param rateY Y方向の拡大率
     */
    private void setChartScale(XYChart.Series<Number, Number> series, double rateX, double rateY) {
        if(chart == null) return;
        if(Settings.ViewRatioNormalize) {    // 表示比正規化
            rateX = (rateX+rateY)/2.0;
            rateY = rateX;
        }
        ObservableList<XYChart.Data<Number, Number>> data = series.getData();
        for(XYChart.Data<Number, Number> d: data) {
            d.getNode().setScaleX(rateX*Settings.ObjMagnification);
            d.getNode().setScaleY(rateY*Settings.ObjMagnification);
        }
    }

    /* 軸メモリ間隔の正規化を行う */
    private void axisNormalize() {
        if(chart == null)
            return;
        double waSize = ((Number)chart.getXAxis().getWidth()).doubleValue();
        double haSize = ((Number)chart.getYAxis().getHeight()).doubleValue();
        double aratio = haSize/waSize;
        double baseSize = widthTVal-widthFVal;
        heightTVal = heightFVal+baseSize*aratio;
        heightT.setDisable(Settings.AxisNormalize);
    }

    /* オブジェクトの追加処理を行う */
    private void addObject() {
        // 属性入力ウィンドウ立ち上げ
        AddObjectController controller = new AddObjectController();
        Stage stage = JavaFXStage.genStage("AddObject", "/fxml/AddObj.fxml", controller, resource);
        stage.showAndWait();

        // 入力内容確認
        if(!controller.inputOK())
            return;
        AddObjectController c = controller;
        Ball ball = new Ball(c.x, c.y, c.diameter, 1.0);
        ball.giveVelocity(c.v0x, c.v0y);
        ball.force(c.ax, c.ay);
        simulator.addObject(c.id, ball);
        plotData();
    }

}

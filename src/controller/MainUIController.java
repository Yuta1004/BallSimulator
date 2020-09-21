package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import util.Parse;

public class MainUIController implements Initializable {

    // UI部品
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedVal;
    @FXML
    private TextField widthF, widthT, heightF, heightT;

    // 描画用
    private double updateSpeed;
    private double widthFVal, widthTVal, heightFVal, heightTVal;

    /* 初期化 */
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // 各種値初期化
        updateSpeed = 1.0;
        widthTVal = 25;
        heightTVal = 10;
        widthFVal = heightFVal = 0;

        // その他色々初期化
        initUI();
    }

    /* UI初期化 */
    private void initUI() {
        // UIイベント<スライダー>
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateSpeed = Math.round(oldVal.doubleValue()*10)/10.0;
            speedVal.setText("x"+updateSpeed);
        });

        // UIイベント<テキスト入力(1行)>
        widthF.textProperty().addListener((obs, oldText, newText) -> {
            Double.parseDouble(newText);
            widthFVal = Parse.parseDouble(newText, 0.0);
        });
        widthT.textProperty().addListener((obs, oldText, newText) -> {
            widthTVal = Parse.parseDouble(newText, 0.0);
        });
        heightF.textProperty().addListener((obs, oldText, newText) -> {
            heightFVal = Parse.parseDouble(newText, 0.0);
        });
        heightT.textProperty().addListener((obs, oldText, newText) -> {
            heightTVal = Parse.parseDouble(newText, 0.0);
        });
    }

}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import util.Parse;

public class AddObjectController implements Initializable {

    // UI
    @FXML
    private Button done;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField xTf, yTf, v0xTf, v0yTf, axTf, ayTf, diameterTf, idTf;

    // 状態管理
    private boolean inpOk;
    public String id, color;
    public double x, y, v0x, v0y, ax, ay, theta, diameter;

    /**
     * 初期化
     */
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        colorPicker.setValue(Color.BROWN);
        done.setOnAction(event -> {
            validateInput();
            done.getScene().getWindow().hide();
        });
    }

    /**
     * 正当な入力が行われたか返す
     */
    public boolean inputOK() {
        return inpOk;
    }

    /**
     * 入力検証
     */
    private void validateInput() {
        // id, 色
        id = idTf.getText();
        color = colorPicker.getValue().toString();

        // その他属性
        inpOk = !id.equals("")
             && (x = Parse.toDouble(xTf.getText(), Double.MIN_VALUE)) != Double.MIN_VALUE
             && (y = Parse.toDouble(yTf.getText(), Double.MIN_VALUE)) != Double.MIN_VALUE
             && (v0x = Parse.toDouble(v0xTf.getText(), Double.MIN_VALUE)) != Double.MIN_VALUE
             && (v0y = Parse.toDouble(v0yTf.getText(), Double.MIN_VALUE)) != Double.MIN_VALUE
             && (ax = Parse.toDouble(axTf.getText(), Double.MIN_VALUE)) != Double.MIN_VALUE
             && (ay = Parse.toDouble(ayTf.getText(), Double.MIN_VALUE)) != Double.MIN_VALUE
             && (diameter = Parse.toDouble(diameterTf.getText(), Double.MIN_VALUE)) != Double.MIN_VALUE;
    }
}
package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import util.Parse;
import data.Settings;

public class SettingsController implements Initializable {

    // UI部品
    @FXML
    private Label objMagValueV;
    @FXML
    private Slider objMagValue;
    @FXML
    private CheckBox viewRatioNormalize, axisNormalize;
    @FXML
    private TextField stepVal;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        // 初期化(UI部品)
        stepVal.setText(""+Settings.StepVal);
        objMagValue.setValue(Settings.ObjMagnification);
        objMagValueV.setText("x "+Settings.ObjMagnification);
        axisNormalize.setSelected(Settings.AxisNormalize);
        viewRatioNormalize.setSelected(Settings.ViewRatioNormalize);

        // 画面表示の拡大率
        objMagValue.valueProperty().addListener((obs, oldVal, newVal) -> {
            double val = Math.round(newVal.doubleValue()*10)/10.0;
            objMagValueV.setText("x "+val);
            Settings.ObjMagnification = val;
        });
        // 表示縦横比正規化
        viewRatioNormalize.setOnAction(event -> {
            if(!Settings.AxisNormalize)
                Settings.ViewRatioNormalize = viewRatioNormalize.isSelected();
            else
                viewRatioNormalize.setSelected(true);
        });
        // 軸間隔正規化
        axisNormalize.setOnAction(event -> {
            boolean state = axisNormalize.isSelected();
            Settings.AxisNormalize = state;
            Settings.ViewRatioNormalize = state;
            viewRatioNormalize.setSelected(state);
        });
        // 1ステップの間隔
        stepVal.textProperty().addListener((obs, oldVal, newVal) -> {
            Settings.StepVal = (int)Parse.toDouble(newVal, 0.0);
        });
    }

}
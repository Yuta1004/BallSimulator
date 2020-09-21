package controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

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
    }

}

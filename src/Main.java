import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;
import java.net.URLClassLoader;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

import util.JavaFXStage;
import controller.MainUIController;

public class Main extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // 画面サイズ取得
        Rectangle2D d = Screen.getPrimary().getVisualBounds();
        int width = (int)Math.min(1280, d.getWidth());
        int height = (int)Math.max(800, d.getHeight());

        // Property
        URL propURLs[] = { getClass().getResource("/fxml/locale/") };
        URLClassLoader urlLoader = new URLClassLoader(propURLs);

        // ウィンドウ起動
        Stage mainStage = JavaFXStage.genStage(
            width,
            height,
            "Ball Simulator",
            "/fxml/MainUI.fxml",
            new MainUIController(),
            ResourceBundle.getBundle("locale", Locale.getDefault(), urlLoader)
        );
        mainStage.show();
    }

}

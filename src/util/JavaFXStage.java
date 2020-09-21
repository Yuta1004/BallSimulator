package util;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ResourceBundle;

public class JavaFXStage {

    /**
     * FXMLファイルを元にStageを生成して返す
     *
     * @param title ウィンドウタイトル
     * @param path FXMLファイルのパス
     * @param controller UIController
     */
    public static <T> Stage genStage(String title, String path, T controller, ResourceBundle resource) {
        return genStage(-1, -1, title, path, controller, resource);
    }

    /**
     * FXMLファイルを元にStageを生成して返す
     *
     * @param w ウィンドウ幅(>=0)
     * @param h ウィンドウ高さ(>=0)
     * @param tilte タイトル
     * @param path FXMLファイルのパス
     * @param controller UIController
     * @param resource ResourceBundle
     */
    public static <T> Stage genStage(int w, int h, String title, String path, T controller, ResourceBundle resource) {
        Scene scene = null;
        try {
            FXMLLoader loader = new FXMLLoader(JavaFXStage.class.getResource(path), resource);
            if(controller != null)
                loader.setController(controller);
            if(w >= 0 && h >= 0)
                scene = new Scene(loader.load(), w, h);
            else
                scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        return stage;
    }

}
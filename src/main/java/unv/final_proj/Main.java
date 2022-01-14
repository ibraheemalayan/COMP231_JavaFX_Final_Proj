package unv.final_proj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unv.final_proj.models.Driver;
import unv.final_proj.models.MediaRental;

import java.io.IOException;

public class Main extends Application {

    static Stage stage;
    static MediaRental sys;

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;

        this.sys = Driver.loadFile();

        HomeScreen main = new HomeScreen();

        Scene root_scene = new Scene(main);

        stage.setTitle("Media Rental");
        stage.setScene(root_scene);
        stage.setMaximized(true);
        stage.setMinHeight(1000);
        stage.setMinWidth(1000);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
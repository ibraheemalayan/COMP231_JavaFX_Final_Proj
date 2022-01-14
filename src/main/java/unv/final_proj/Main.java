package unv.final_proj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;

        HomeScreen main = new HomeScreen();

        Scene root_scene = new Scene(main);

        stage.setTitle("Media Rental");
        stage.setScene(root_scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
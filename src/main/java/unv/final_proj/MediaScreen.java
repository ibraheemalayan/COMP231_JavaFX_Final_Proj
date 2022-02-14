package unv.final_proj;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;

public class MediaScreen extends StackPane {

    public MediaScreen() {

        this.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("customer_styles.css")).toExternalForm());

        this.setPadding(new Insets(40, 40, 40, 40));

        this.getStyleClass().add("light-bg");

        VBox all = new VBox();
        all.setAlignment(Pos.CENTER);

        HBox main = new HBox();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(30);

        Image illustration_svg = new Image(Objects.requireNonNull(MediaScreen.class.getResourceAsStream("media_illustration.png")));
        ImageView illustration = new ImageView(illustration_svg);

        illustration.setPreserveRatio(true);

        illustration.fitHeightProperty().bind(main.heightProperty().divide(5).multiply(3));

        VBox btns_pane = new VBox(40);
        btns_pane.setAlignment(Pos.CENTER);

        btns_pane.setPrefSize(300,700);
        btns_pane.setMaxSize(300,700);


        Image add_img_png = new Image(Objects.requireNonNull(HomeScreen.class.getResourceAsStream("add.png")));
        ImageView add_img = new ImageView(add_img_png);
        add_img.setFitHeight(40);
        add_img.setPreserveRatio(true);

        Image edit_img_png = new Image(Objects.requireNonNull(HomeScreen.class.getResourceAsStream("edit.png")));
        ImageView edit_img = new ImageView(edit_img_png);
        edit_img.setFitHeight(40);
        edit_img.setPreserveRatio(true);

        Image del_img_png = new Image(Objects.requireNonNull(HomeScreen.class.getResourceAsStream("delete.png")));
        ImageView del_img = new ImageView(del_img_png);
        del_img.setFitHeight(40);
        del_img.setPreserveRatio(true);

        Image search_img_png = new Image(Objects.requireNonNull(HomeScreen.class.getResourceAsStream("search.png")));
        ImageView search_img = new ImageView(search_img_png);
        search_img.setFitHeight(40);
        search_img.setPreserveRatio(true);


        Pane transparent_pane = new Pane();
        transparent_pane.getStyleClass().add("transparent_pane");


        Button add_btn =    new Button("  Add   ", add_img);
        Button edit_btn =   new Button("  Edit  ", edit_img);
        Button del_btn =    new Button("  Delete", del_img);
        Button search_btn = new Button("  Search", search_img);

        add_btn.getStyleClass().add("btn");
        edit_btn.getStyleClass().add("btn");
        del_btn.getStyleClass().add("btn");
        search_btn.getStyleClass().add("btn");

        search_btn.getStyleClass().add("dark-bg");


        btns_pane.getChildren().add(add_btn);
        btns_pane.getChildren().add(edit_btn);
        btns_pane.getChildren().add(del_btn);
        btns_pane.getChildren().add(transparent_pane);
        btns_pane.getChildren().add(search_btn);

        VBox.setVgrow(add_btn, Priority.ALWAYS);
        VBox.setVgrow(edit_btn, Priority.ALWAYS);
        VBox.setVgrow(del_btn, Priority.ALWAYS);
        VBox.setVgrow(search_btn, Priority.ALWAYS);

        Button back_btn = new Button("Back");
        back_btn.getStyleClass().add("back-button");

        all.getChildren().add(back_btn);

        main.getChildren().add(btns_pane);
        main.getChildren().add(illustration);

        all.getChildren().add(main);

        back_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new HomeScreen());
        });

        this.getChildren().add(all);

        this.getStyleClass().add("light-bg");

        add_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new MediaForm("Add"));
        });
        edit_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new MediaForm("Edit"));
        });
        del_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new MediaForm("Delete"));
        });
        search_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new MediaForm("Search"));
        });

    }
}

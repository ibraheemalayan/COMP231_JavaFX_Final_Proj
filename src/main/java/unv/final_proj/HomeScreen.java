package unv.final_proj;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class HomeScreen extends StackPane {

    public HomeScreen() {

        this.getStylesheets().add(Main.class.getResource("home_styles.css").toExternalForm());

        this.setPadding(new Insets(80, 80, 80, 80));

        this.getStyleClass().add("light-bg");

        VBox main = new VBox();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(30);

        Image illustration_svg = new Image(HomeScreen.class.getResourceAsStream("illustration.png"));
        ImageView illustration = new ImageView(illustration_svg);

        illustration.setPreserveRatio(true);

        illustration.fitHeightProperty().bind(main.heightProperty().divide(5).multiply(4));

        HBox btns_pane = new HBox(80);

        btns_pane.setPrefHeight(94);
        btns_pane.minWidth(600);
        btns_pane.setAlignment(Pos.CENTER);
        btns_pane.prefWidthProperty().bind(main.widthProperty().divide(3).multiply(2));
        btns_pane.maxWidthProperty().bind(main.widthProperty());


        Image img_1 = new Image(HomeScreen.class.getResourceAsStream("customers.png"));
        ImageView img = new ImageView(img_1);
        img.setFitHeight(40);
        img.setPreserveRatio(true);

        Image img_2 = new Image(HomeScreen.class.getResourceAsStream("media.png"));
        ImageView img2 = new ImageView(img_2);
        img2.setFitHeight(40);
        img2.setPreserveRatio(true);

        Pane transparent_pane = new Pane();
        transparent_pane.getStyleClass().add("transparent_pane");


        Button customers_btn = new Button("Customers", img);
        Button media_btn = new Button("Media", img2);
        Button rent_btn = new Button("Rent System");

        customers_btn.getStyleClass().add("btn");
        media_btn.getStyleClass().add("btn");

        rent_btn.getStyleClass().add("dark-btn");

        btns_pane.getChildren().add(customers_btn);
        btns_pane.getChildren().add(media_btn);
        btns_pane.getChildren().add(transparent_pane);
        btns_pane.getChildren().add(rent_btn);

        btns_pane.setHgrow(customers_btn, Priority.ALWAYS);
        btns_pane.setHgrow(media_btn, Priority.ALWAYS);
        btns_pane.setHgrow(rent_btn, Priority.ALWAYS);

        main.getChildren().add(illustration);
        main.getChildren().add(btns_pane);

        this.getChildren().add(main);

        this.getStyleClass().add("light-bg");

        customers_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new CustomersScreen());
        });

        media_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new MediaScreen());
        });

    }
}

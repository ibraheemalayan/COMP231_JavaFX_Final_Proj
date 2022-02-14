package unv.final_proj;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import unv.final_proj.models.Media;

import java.util.ArrayList;
import java.util.Objects;

public class ListScreen extends VBox {

    public ListScreen(ArrayList<Media> media){

        this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("customer_form_styles.css")).toExternalForm());

        this.setPadding(new Insets(60));
        this.setSpacing(40);

        for ( Media m : media ) {

            Label lbl = new Label(m.toString());
            this.getChildren().add(lbl);

        }



    }

}

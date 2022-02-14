package unv.final_proj;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import unv.final_proj.models.*;

import java.time.LocalDate;
import java.util.Objects;

public class RentScreen extends StackPane {

    public RentScreen() {

        this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("customer_form_styles.css")).toExternalForm());

        this.setPadding(new Insets(0, 200, 0, 200));

        this.getStyleClass().add("light-bg");

        VBox all = new VBox();
        all.setAlignment(Pos.CENTER);

        /////////////////////////////////////////////////////////////////////////////

        Insets margin_ins = new Insets(20,30,20,30);

        /////////////////////////////////////////////////////////////////////////////

        Button back_btn = new Button("Back");
        back_btn.getStyleClass().add("back-button");

        back_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new HomeScreen());
        });

        BorderPane back_btn_cont = new BorderPane();

        back_btn_cont.setLeft(back_btn);

        LocalDate now = LocalDate.now();

        Text rent_date = new Text("Rent Date: " + now.toString());
        back_btn_cont.setRight(rent_date);

        rent_date.getStyleClass().add("small-text");

        back_btn_cont.setPadding(new Insets(30));

        all.getChildren().add(back_btn_cont);

        /////////////////////////////////////////////////////////////////////////////

        Text form_title = new Text("Rent Media Form");
        form_title.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 30));
        form_title.getStyleClass().add("text");

        all.getChildren().add(form_title);

        /////////////////////////////////////////////////////////////////////////////

        BorderPane customer_cont = new BorderPane();

        customer_cont.getStyleClass().add("has-top-border");

        VBox.setMargin(customer_cont, margin_ins);

        Text customer_id_lbl = new Text("Customer ID");
        customer_id_lbl.getStyleClass().add("label-text");

        TextField customer_id_tb = new TextField();

        Button search_customer_btn = new Button("Search");

        customer_cont.setLeft(customer_id_lbl);
        customer_cont.setCenter(customer_id_tb);
        customer_cont.setRight(search_customer_btn);

        BorderPane.setMargin(customer_id_lbl, margin_ins);
        BorderPane.setMargin(customer_id_tb, margin_ins);
        BorderPane.setMargin(search_customer_btn, margin_ins);

        all.getChildren().add(customer_cont);

        /////////////////////////////////////////////////////////////////////////////

        GridPane customer_data_grid = new GridPane();

        customer_data_grid.setHgap(100);
        customer_data_grid.setVgap(30);
        customer_data_grid.setAlignment(Pos.CENTER);
        customer_data_grid.getStyleClass().add("grid-pane");

        Text name_text = new Text("Name");
        Text address_text = new Text("Address");
        Text mobile_text = new Text("Mobile");
        Text plan_text = new Text("Plan");

        Label name_lbl = new Label("                  ");
        Label address_lbl = new Label("                  ");
        Label mobile_lbl = new Label("                  ");
        Label plan_lbl = new Label("                  ");

        name_text.getStyleClass().add("text");
        address_text.getStyleClass().add("text");
        mobile_text.getStyleClass().add("text");
        plan_text.getStyleClass().add("text");

        customer_data_grid.add(name_text, 0, 0);
        customer_data_grid.add(address_text, 0, 1);
        customer_data_grid.add(mobile_text, 2, 0);
        customer_data_grid.add(plan_text, 2, 1);

        customer_data_grid.add(name_lbl, 1, 0);
        customer_data_grid.add(address_lbl, 1, 1);
        customer_data_grid.add(mobile_lbl, 3, 0);
        customer_data_grid.add(plan_lbl, 3, 1);

        all.getChildren().add(customer_data_grid);

        /////////////////////////////////////////////////////////////////////////////

        Label status = new Label("Waiting Submission");

        /////////////////////////////////////////////////////////////////////////////

        search_customer_btn.setOnAction(
                (event) -> {


                    if (customer_id_tb.getText().length() < 1) {
                        status.setText("Enter a valid ID");
                        status.getStyleClass().remove("success-label");
                        if (! status.getStyleClass().contains("warning-label") ){
                            status.getStyleClass().add("warning-label");
                        }

                        return;
                    }

                    Customer c = Main.sys.searchCustomerByID(customer_id_tb.getText());

                    if (c == null) {
                        status.setText("Customer with this ID doesn't exist !");

                        status.getStyleClass().remove("success-label");
                        if (! status.getStyleClass().contains("warning-label") ){
                            status.getStyleClass().add("warning-label");
                        }

                        name_lbl.setText("                  ");
                        address_lbl.setText("                  ");
                        mobile_lbl.setText("                  ");
                        plan_lbl.setText("                  ");

                        return;
                    }

                    name_lbl.setText(c.getName());
                    address_lbl.setText(c.getAddress());
                    customer_id_tb.setText(c.getId());
                    mobile_lbl.setText(c.getMobile());
                    plan_lbl.setText(c.getPlan().name());

                    status.setText("Found Customer");

                    status.getStyleClass().remove("warning-label");
                    if (! status.getStyleClass().contains("success-label") ){
                        status.getStyleClass().add("success-label");
                    }


                }
        );

        /////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////////////

        BorderPane media_cont = new BorderPane();

        media_cont.getStyleClass().add("has-top-border");

        VBox.setMargin(media_cont, margin_ins);

        Text media_id_lbl = new Text("Media Code");
        media_id_lbl.getStyleClass().add("label-text");

        TextField media_id_tb = new TextField();

        Button search_media_btn = new Button("Search");

        media_cont.setLeft(media_id_lbl);
        media_cont.setCenter(media_id_tb);
        media_cont.setRight(search_media_btn);

        BorderPane.setMargin(media_id_lbl, margin_ins);
        BorderPane.setMargin(media_id_tb, margin_ins);
        BorderPane.setMargin(search_media_btn, margin_ins);

        all.getChildren().add(media_cont);

        /////////////////////////////////////////////////////////////////////////////

        GridPane media_data_grid = new GridPane();

        media_data_grid.setHgap(100);
        media_data_grid.setVgap(30);
        media_data_grid.setAlignment(Pos.CENTER);
        media_data_grid.getStyleClass().add("grid-pane");

        Text title_text = new Text("Title");
        Text copies_text = new Text("Available Copies");
        Text type_text = new Text("Type");

        Label title_lbl = new Label("                  ");
        Label copies_lbl = new Label("                  ");
        Label type_lbl = new Label("                  ");

        title_text.getStyleClass().add("text");
        copies_text.getStyleClass().add("text");
        type_text.getStyleClass().add("text");

        media_data_grid.add(title_text, 0, 0);
        media_data_grid.add(copies_text, 0, 1);
        media_data_grid.add(type_text, 2, 0);

        media_data_grid.add(title_lbl, 1, 0);
        media_data_grid.add(copies_lbl, 1, 1);
        media_data_grid.add(type_lbl, 3, 0);

        all.getChildren().add(media_data_grid);

        /////////////////////////////////////////////////////////////////////////////

        search_media_btn.setOnAction(
                (event) -> {


                    if (media_id_tb.getText().length() < 1) {
                        status.setText("Enter a valid Code");

                        status.getStyleClass().remove("success-label");
                        if (! status.getStyleClass().contains("warning-label") ){
                            status.getStyleClass().add("warning-label");
                        }

                        return;
                    }

                    Media m = Main.sys.searchMediaByCode(media_id_tb.getText());

                    if (m == null) {
                        status.setText("Media with this code doesn't exist !");

                        status.getStyleClass().remove("success-label");
                        if (! status.getStyleClass().contains("warning-label") ){
                            status.getStyleClass().add("warning-label");
                        }

                        return;
                    }

                    media_id_tb.setText(m.getCode());
                    title_lbl.setText(m.getTitle());
                    copies_lbl.setText("" + m.getNum_of_available_copies());
                    type_lbl.setText(m.getClass().getSimpleName());

                    status.setText("Found Media");

                    status.getStyleClass().remove("warning-label");
                    if (! status.getStyleClass().contains("success-label") ){
                        status.getStyleClass().add("success-label");
                    }

                }
        );



        /////////////////////////////////////////////////////////////////////////////

        BorderPane status_cont = new BorderPane();

        status_cont.setCenter(status);

        BorderPane.setMargin(status, margin_ins);

        /////////////////////////////////////////////////////////////////////////////

        Button add_to_cart = new Button("Add To Cart");
        Button process_cart = new Button("Process Cart");
        Button process_all_carts = new Button("Process All Carts");

        add_to_cart.setOnAction( (event) -> {

            search_customer_btn.getOnAction().handle(new ActionEvent());
            if (! status.getStyleClass().contains("success-label") ){
                return;
            }

            search_media_btn.getOnAction().handle(new ActionEvent());
            if (! status.getStyleClass().contains("success-label") ){
                return;
            }

            if(Main.sys.addToCart(customer_id_tb.getText(), media_id_tb.getText())){
                status.setText("Added To Cart Successfully");

                status.getStyleClass().remove("warning-label");
                if (! status.getStyleClass().contains("success-label") ){
                    status.getStyleClass().add("success-label");
                }

            }else{
                status.setText("Adding To Cart Failed");

                status.getStyleClass().remove("success-label");
                if (! status.getStyleClass().contains("warning-label") ){
                    status.getStyleClass().add("warning-label");
                }

            }

        });

        process_cart.setOnAction( (event) -> {

            search_customer_btn.getOnAction().handle(new ActionEvent());
            if (! status.getStyleClass().contains("success-label") ){
                return;
            }

            Customer c = Main.sys.searchCustomerByID(customer_id_tb.getText());

            Stage st = new Stage();

            String res = c.proccess_requests();

            if ( res.length() == 0 ){
                res = "Nothing To Process";
            }

            ProcessResultsScreen pr = new ProcessResultsScreen(res);

            Main.sys.update_file();

            Scene root_scene = new Scene(pr);

            st.setTitle("Cart Processing Results");
            st.setScene(root_scene);
            st.setMinHeight(100);
            st.setMinWidth(200);
            st.show();

            status.setText("Processed Cart");
            status.getStyleClass().remove("warning-label");
            if (! status.getStyleClass().contains("success-label") ){
                status.getStyleClass().add("success-label");
            }

        });

        process_all_carts.setOnAction( (event) -> {

            Stage st = new Stage();

            String res = Main.sys.processRequests();

            if ( res.length() == 0 ){
                res = "Nothing To Process";
            }

            ProcessResultsScreen pr = new ProcessResultsScreen(res);

            Main.sys.update_file();

            Scene root_scene = new Scene(pr);

            st.setTitle("Cart Processing Results");
            st.setScene(root_scene);
            st.setMinHeight(100);
            st.setMinWidth(200);
            st.show();

            status.setText("Processed All Carts");
            status.getStyleClass().remove("warning-label");
            if (! status.getStyleClass().contains("success-label") ){
                status.getStyleClass().add("success-label");
            }

        });

        HBox btns = new HBox();
        btns.setAlignment(Pos.CENTER);
        btns.setSpacing(30);

        HBox.setMargin(add_to_cart, margin_ins);
        HBox.setMargin(process_cart, margin_ins);
        HBox.setMargin(process_all_carts, margin_ins);

        btns.getChildren().add(add_to_cart);
        btns.getChildren().add(process_cart);
        btns.getChildren().add(process_all_carts);

        btns.getStyleClass().add("has-top-border");

        VBox.setMargin(btns, margin_ins);

        all.getChildren().add(btns);

        status_cont.getStyleClass().add("has-top-border");

        all.getChildren().add(status_cont);



        /////////////////////////////////////////////////////////////////////////////


















        this.getChildren().add(all);

        this.getStyleClass().add("light-bg");

    }


}

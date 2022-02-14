package unv.final_proj;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import unv.final_proj.models.Customer;
import unv.final_proj.models.PlanType;

import java.util.Objects;

public class CustomerForm extends StackPane {

    public CustomerForm(String Operation) {

        this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("customer_form_styles.css")).toExternalForm());

        this.setPadding(new Insets(40, 40, 40, 40));

        this.getStyleClass().add("light-bg");

        VBox all = new VBox();
        all.setAlignment(Pos.CENTER);

        HBox main = new HBox();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(30);

        Image illustration_svg = new Image(Objects.requireNonNull(CustomerForm.class.getResourceAsStream("customers_illustration.png")));
        ImageView illustration = new ImageView(illustration_svg);

        illustration.setPreserveRatio(true);

        illustration.fitHeightProperty().bind(main.heightProperty().divide(5).multiply(3));

        GridPane grid = new GridPane();
        grid.setHgap(150);
        grid.setVgap(40);
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("grid-pane");

        Text form_title = new Text(Operation + " Customer Form");

        form_title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        grid.add(form_title, 0, 0, 2, 1);

        Label userID = new Label("User ID");
        grid.add(userID, 0, 1);

        TextField user_id_tf = new TextField();

        grid.add(user_id_tf, 1, 1);

/////////////////////////////////////////////////////////////////////////////

        Label userName = new Label("User Name");
        grid.add(userName, 0, 2);

        TextField user_name_tf = new TextField();


        user_name_tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (user_id_tf.getText().length() < 1) {
                    user_name_tf.setText("Fill the ID Field !");

                }
            }
        });

        grid.add(user_name_tf, 1, 2);

        /////////////////////////////////////////////////////////////////////////////

        Label userAddress = new Label("User Address");
        grid.add(userAddress, 0, 3);

        TextField user_addr_tf = new TextField();


        user_addr_tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (user_name_tf.getText().length() < 1) {
                    user_addr_tf.setText("Fill the Name Field !");
                }
            }
        });

        grid.add(user_addr_tf, 1, 3);

        /////////////////////////////////////////////////////////////////////////////

        Label userMobile = new Label("User Mobile");
        grid.add(userMobile, 0, 4);

        TextField user_mob_tf = new TextField();


        user_mob_tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (user_addr_tf.getText().length() < 1) {
                    user_mob_tf.setText("Fill the Address Field !");
                }
            }
        });

        grid.add(user_mob_tf, 1, 4);

        /////////////////////////////////////////////////////////////////////////////

        Label userPlan = new Label("Plan Type");
        grid.add(userPlan, 0, 5);

        ToggleGroup plan_tg = new ToggleGroup();

        RadioButton limited_plan = new RadioButton("Limited");
        RadioButton unlimited_plan = new RadioButton("Unlimited");

        limited_plan.setToggleGroup(plan_tg);
        unlimited_plan.setToggleGroup(plan_tg);

        HBox plan_hbox = new HBox();
        plan_hbox.getChildren().add(limited_plan);
        plan_hbox.getChildren().add(unlimited_plan);

        grid.add(plan_hbox, 1, 5);


        ////////////////////////////// Submission ////////////////////////////

        Label st_lbl = new Label("Status");
        Label status = new Label("Waiting Submission");

        grid.add(st_lbl, 0, 6);
        grid.add(status, 1, 6);

        Button submit = new Button(Operation);
        submit.getStyleClass().add("small_btn");

        submit.setOnAction((event) -> {    // lambda expression

            switch (Operation) {
                case "Add" -> {

                    if (user_id_tf.getText().length() < 1) {
                        status.setText("Enter a valid ID");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (user_name_tf.getText().length() < 1) {
                        status.setText("Enter a valid Name");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (user_addr_tf.getText().length() < 1) {
                        status.setText("Enter a valid Address");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (user_mob_tf.getText().length() < 1) {
                        status.setText("Enter a valid Mobile");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    Toggle selected = plan_tg.getSelectedToggle();
                    if (selected == null) {
                        status.setText("Select a Plan");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    String plan = (selected.equals(limited_plan)) ? "LIMITED" : "UNLIMITED";
                    try {
                        Main.sys.addCustomer(user_id_tf.getText(), user_mob_tf.getText(), user_name_tf.getText(), user_addr_tf.getText(), plan);
                        status.setText("Added Successfully");
                        status.getStyleClass().remove("warning-label");
                        status.getStyleClass().add("success-label");
                    } catch (IllegalArgumentException e) {
                        status.setText(e.getMessage());
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                    }

                }
                case "Delete" -> {

                    if (user_id_tf.getText().length() < 1) {
                        status.setText("Enter a valid ID");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    Customer c = Main.sys.deleteCustomerByID(user_id_tf.getText());

                    if (c == null) {
                        status.setText("Customer with this ID doesn't exist !");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    user_addr_tf.setText(c.getAddress());
                    user_id_tf.setText(c.getId());
                    user_mob_tf.setText(c.getMobile());
                    user_name_tf.setText(c.getName());

                    Toggle selected = (c.getPlan().equals(PlanType.LIMITED)) ? limited_plan : unlimited_plan;

                    plan_tg.selectToggle(selected);

                    status.setText("Deleted Successfully");
                    status.getStyleClass().remove("warning-label");
                    status.getStyleClass().add("success-label");

                }
                case "Edit" -> {

                    if (user_id_tf.getText().length() < 1) {
                        status.setText("Enter a valid ID");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (user_name_tf.getText().length() < 1) {
                        status.setText("Enter a valid Name");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (user_addr_tf.getText().length() < 1) {
                        status.setText("Enter a valid Address");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (user_mob_tf.getText().length() < 1) {
                        status.setText("Enter a valid Mobile");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    if (Main.sys.searchCustomerByID(user_id_tf.getText()) == null) {
                        status.setText("Customer with this ID doesn't exist !");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    String plan_txt = "LIMITED";
                    PlanType plan = PlanType.valueOf(plan_txt.toUpperCase());
                    try {

                        Main.sys.editCustomer(user_id_tf.getText(), user_mob_tf.getText(), user_name_tf.getText(), user_addr_tf.getText(), plan);
                        status.setText("Edited Successfully");
                        status.getStyleClass().remove("warning-label");
                        status.getStyleClass().add("success-label");

                    } catch (IllegalArgumentException e) {
                        status.setText(e.getMessage());
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                    }

                }
                case "Search" -> {

                    if (user_id_tf.getText().length() < 1) {
                        status.setText("Enter a valid ID");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");

                        return;
                    }

                    Customer c = Main.sys.searchCustomerByID(user_id_tf.getText());

                    if (c == null) {
                        status.setText("Customer with this ID doesn't exist !");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    user_name_tf.setText(c.getName());
                    user_addr_tf.setText(c.getAddress());
                    user_id_tf.setText(c.getId());
                    user_mob_tf.setText(c.getMobile());


                    Toggle selected = (c.getPlan().equals(PlanType.LIMITED)) ? limited_plan : unlimited_plan;

                    plan_tg.selectToggle(selected);

                    status.setText("Found !");
                    status.getStyleClass().remove("warning-label");
                    status.getStyleClass().add("success-label");

                }
            }

        });

        grid.add(submit, 1, 7);

        /////////////////////////////////////////////////////////////////////////////


        if(Operation.equals("Delete") || Operation.equals("Search") ){
            user_addr_tf.setDisable(true);
            user_mob_tf.setDisable(true);
            user_name_tf.setDisable(true);
            limited_plan.setDisable(true);
            unlimited_plan.setDisable(true);
        }



        /////////////////////////////////////////////////////////////////////////////



        Button back_btn = new Button("Back");
        back_btn.getStyleClass().add("back-button");

        all.getChildren().add(back_btn);

        main.getChildren().add(grid);
        main.getChildren().add(illustration);

        all.getChildren().add(main);

        back_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new CustomersScreen());
        });

        this.getChildren().add(all);

        this.getStyleClass().add("light-bg");
    }
}

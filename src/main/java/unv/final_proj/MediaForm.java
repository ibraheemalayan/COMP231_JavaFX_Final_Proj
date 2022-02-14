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
import unv.final_proj.models.*;

import java.util.Objects;

public class MediaForm extends StackPane {

    public MediaForm(String Operation) {

        this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("customer_form_styles.css")).toExternalForm());

        this.setPadding(new Insets(40, 40, 40, 40));

        this.getStyleClass().add("light-bg");

        VBox all = new VBox();
        all.setAlignment(Pos.CENTER);

        HBox main = new HBox();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(30);

        Image illustration_svg = new Image(Objects.requireNonNull(MediaForm.class.getResourceAsStream("media_illustration.png")));
        ImageView illustration = new ImageView(illustration_svg);

        illustration.setPreserveRatio(true);

        illustration.fitHeightProperty().bind(main.heightProperty().divide(5).multiply(3));

        GridPane grid = new GridPane();
        grid.setHgap(150);
        grid.setVgap(40);
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("grid-pane");

        Text form_title = new Text(Operation + " Media Form");

        form_title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        grid.add(form_title, 0, 0, 2, 1);

        Label mediaCode = new Label("Media Code");
        grid.add(mediaCode, 0, 1);

        TextField media_code_tf = new TextField();

        grid.add(media_code_tf, 1, 1);

        /////////////////////////////////////////////////////////////////////////////

        Label mediaTitle = new Label("Media Title");
        grid.add(mediaTitle, 0, 2);

        TextField media_title_tf = new TextField();


        media_title_tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (media_code_tf.getText().length() < 1) {
                    media_title_tf.setText("Fill the Code Field !");

                }
            }
        });

        grid.add(media_title_tf, 1, 2);

        /////////////////////////////////////////////////////////////////////////////

        Label AvailableCopies = new Label("Available Copies");
        grid.add(AvailableCopies, 0, 3);

        TextField copies_tf = new TextField();


        copies_tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    copies_tf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        grid.add(copies_tf, 1, 3);

        /////////////////////////////////////////////////////////////////////////////

        Label mediaType = new Label("Type");
        grid.add(mediaType, 0, 4);

        ToggleGroup type_tg = new ToggleGroup();

        RadioButton game_rb = new RadioButton("Game");
        RadioButton album_rb = new RadioButton("Album");
        RadioButton movie_rb = new RadioButton("Movie");

        game_rb.setToggleGroup(type_tg);
        album_rb.setToggleGroup(type_tg);
        movie_rb.setToggleGroup(type_tg);

        HBox types_hbox = new HBox();
        types_hbox.getChildren().add(game_rb);
        types_hbox.getChildren().add(album_rb);
        types_hbox.getChildren().add(movie_rb);

        grid.add(types_hbox, 1, 4);


        ////////////////////////////// Submission ////////////////////////////

        Label st_lbl = new Label("Status");
        Label status = new Label("Waiting Submission");

        grid.add(st_lbl, 0, 7);
        grid.add(status, 1, 7);

        Button submit = new Button(Operation);
        submit.getStyleClass().add("small_btn");

        /////////////////////////////////////////////////////////////////////////////

        Label gameWeight = new Label("Game Weight");
        TextField game_weight_tf = new TextField();

        Label albumArtisit = new Label("Album Artist");
        TextField album_artist_tf = new TextField();
        Label albumSongs = new Label("Album Songs");
        TextField album_songs_tf = new TextField();

        Label movieRating = new Label("Movie Rating");
        TextField movie_rating_tf = new TextField();

        Pane spring = new Pane();
        spring.minHeightProperty().bind(copies_tf.heightProperty());
        Pane spring2 = new Pane();
        spring2.minHeightProperty().bind(copies_tf.heightProperty());

        grid.add(spring2, 0, 5);
        grid.add(spring, 0, 6);

        type_tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                grid.getChildren().remove(gameWeight);
                grid.getChildren().remove(game_weight_tf);

                grid.getChildren().remove(albumArtisit);
                grid.getChildren().remove(album_artist_tf);
                grid.getChildren().remove(albumSongs);
                grid.getChildren().remove(album_songs_tf);

                grid.getChildren().remove(movieRating);
                grid.getChildren().remove(movie_rating_tf);

                grid.getChildren().remove(spring);
                grid.getChildren().remove(spring2);

                if (type_tg.getSelectedToggle().equals(game_rb)) {


                    grid.add(gameWeight, 0, 5);
                    grid.add(game_weight_tf, 1, 5);

                    grid.add(spring, 0, 6);


                }else if (type_tg.getSelectedToggle().equals(album_rb)) {

                    grid.add(albumArtisit, 0, 5);
                    grid.add(album_artist_tf, 1, 5);
                    grid.add(albumSongs, 0, 6);
                    grid.add(album_songs_tf, 1, 6);

                }else if (type_tg.getSelectedToggle().equals(movie_rb)) {

                    grid.add(movieRating, 0, 5);
                    grid.add(movie_rating_tf, 1, 5);

                    grid.add(spring, 0, 6);
                }
            }
        });

        /////////////////////////////////////////////////////////////////////////////

        submit.setOnAction((event) -> {    // lambda expression

            switch (Operation) {
                case "Add" -> {

                    if (media_code_tf.getText().length() < 1) {
                        status.setText("Enter a valid Code");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (media_title_tf.getText().length() < 1) {
                        status.setText("Enter a valid Title");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (copies_tf.getText().length() < 1) {
                        status.setText("Enter a valid Number of Copies");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    Toggle selected = type_tg.getSelectedToggle();

                    if (selected == null) {
                        status.setText("Select a Type");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    if (selected.equals(game_rb)) {

                        if (game_weight_tf.getText().length() < 1) {
                            status.setText("Enter a valid Game Weight");
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                            return;
                        }

                        try {
                            Main.sys.addGame(media_code_tf.getText(), media_title_tf.getText(), Integer.parseInt(copies_tf.getText()), Double.parseDouble(game_weight_tf.getText()));
                            status.setText("Added Successfully");
                            status.getStyleClass().remove("warning-label");
                            status.getStyleClass().add("success-label");
                        } catch (Exception e) {
                            status.setText(e.getMessage());
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                        }


                    } else if (selected.equals(album_rb)) {

                        if (album_songs_tf.getText().length() < 1) {
                            status.setText("Enter album songs");
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                            return;
                        }
                        if (album_artist_tf.getText().length() < 1) {
                            status.setText("Enter a valid Artist Name");
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                            return;
                        }

                        try {
                            Main.sys.addAlbum(media_code_tf.getText(), media_title_tf.getText(), Integer.parseInt(copies_tf.getText()), album_artist_tf.getText(), album_songs_tf.getText());
                            status.setText("Added Successfully");
                            status.getStyleClass().remove("warning-label");
                            status.getStyleClass().add("success-label");
                        } catch (Exception e) {
                            status.setText(e.getMessage());
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                        }

                    } else if (selected.equals(movie_rb)) {

                        if (movie_rating_tf.getText().length() < 1) {
                            status.setText("Enter a valid Movie Rating");
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                            return;
                        }

                        try {
                            Main.sys.addMovie(media_code_tf.getText(), media_title_tf.getText(), Integer.parseInt(copies_tf.getText()), movie_rating_tf.getText());
                            status.setText("Added Successfully");
                            status.getStyleClass().remove("warning-label");
                            status.getStyleClass().add("success-label");
                        } catch (Exception e) {
                            status.setText(e.getMessage());
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                        }

                    }
                }
                case "Delete" -> {
                    if (media_code_tf.getText().length() < 1) {
                        status.setText("Enter a valid Code");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    Media m = Main.sys.deleteMediaByCode(media_code_tf.getText());

                    if (m == null) {
                        status.setText("Media with this code doesn't exist !");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    media_code_tf.setText(m.getCode());
                    media_title_tf.setText(m.getTitle());
                    copies_tf.setText("" + m.getNum_of_available_copies());

                    if (m instanceof Game) {
                        type_tg.selectToggle(game_rb);
                        game_weight_tf.setText(((Game) m).getWeight() + "");
                    } else if (m instanceof Movie) {
                        type_tg.selectToggle(movie_rb);
                        movie_rating_tf.setText(((Movie) m).getRating() + "");
                    } else if (m instanceof Album) {
                        type_tg.selectToggle(album_rb);
                        album_songs_tf.setText(((Album) m).getSongs());
                        album_artist_tf.setText(((Album) m).getArtist());
                    }

                    status.setText("Removed form customers' carts, returned by renters, and deleted successfully");
                    status.getStyleClass().remove("warning-label");
                    status.getStyleClass().add("success-label");
                }
                case "Edit" -> {

                    if (media_code_tf.getText().length() < 1) {
                        status.setText("Enter a valid Code");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (media_title_tf.getText().length() < 1) {
                        status.setText("Enter a valid Title");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }
                    if (copies_tf.getText().length() < 1) {
                        status.setText("Enter a valid Number of Copies");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    Toggle selected = type_tg.getSelectedToggle();

                    if (selected == null) {
                        status.setText("Select a Type");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    if (selected.equals(game_rb)) {

                        if (game_weight_tf.getText().length() < 1) {
                            status.setText("Enter a valid Game Weight");
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                            return;
                        }

                        try {
                            Main.sys.editGame(media_code_tf.getText(), media_title_tf.getText(), Integer.parseInt(copies_tf.getText()), Double.parseDouble(game_weight_tf.getText()));
                            status.setText("Edited Game Successfully");
                            status.getStyleClass().remove("warning-label");
                            status.getStyleClass().add("success-label");
                        } catch (Exception e) {
                            status.setText(e.getMessage());
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                        }


                    } else if (selected.equals(album_rb)) {

                        if (album_songs_tf.getText().length() < 1) {
                            status.setText("Enter album songs");
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                            return;
                        }
                        if (album_artist_tf.getText().length() < 1) {
                            status.setText("Enter a valid Artist Name");
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                            return;
                        }

                        try {
                            Main.sys.editAlbum(media_code_tf.getText(), media_title_tf.getText(), Integer.parseInt(copies_tf.getText()), album_artist_tf.getText(), album_songs_tf.getText());
                            status.setText("Edited Album Successfully");
                            status.getStyleClass().remove("warning-label");
                            status.getStyleClass().add("success-label");
                        } catch (Exception e) {
                            status.setText(e.getMessage());
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                        }

                    } else if (selected.equals(movie_rb)) {

                        if (movie_rating_tf.getText().length() < 1) {
                            status.setText("Enter a valid Movie Rating");
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                            return;
                        }

                        try {
                            Main.sys.editMovie(media_code_tf.getText(), media_title_tf.getText(), Integer.parseInt(copies_tf.getText()), movie_rating_tf.getText());
                            status.setText("Edited Movie Successfully");
                            status.getStyleClass().remove("warning-label");
                            status.getStyleClass().add("success-label");
                        } catch (Exception e) {
                            status.setText(e.getMessage());
                            status.getStyleClass().remove("success-label");
                            status.getStyleClass().add("warning-label");
                        }

                    }
                }
                case "Search" -> {
                    if (media_code_tf.getText().length() < 1) {
                        status.setText("Enter a valid Code");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    Media m = Main.sys.searchMediaByCode(media_code_tf.getText());

                    if (m == null) {
                        status.setText("Media with this code doesn't exist !");
                        status.getStyleClass().remove("success-label");
                        status.getStyleClass().add("warning-label");
                        return;
                    }

                    media_code_tf.setText(m.getCode());
                    media_title_tf.setText(m.getTitle());
                    copies_tf.setText("" + m.getNum_of_available_copies());

                    if (m instanceof Game) {
                        type_tg.selectToggle(game_rb);
                        game_weight_tf.setText(((Game) m).getWeight() + "");
                    } else if (m instanceof Movie) {
                        type_tg.selectToggle(movie_rb);
                        movie_rating_tf.setText(((Movie) m).getRating() + "");
                    } else if (m instanceof Album) {
                        type_tg.selectToggle(album_rb);
                        album_songs_tf.setText(((Album) m).getSongs());
                        album_artist_tf.setText(((Album) m).getArtist());
                    }

                    status.setText("Found Media");
                    status.getStyleClass().remove("warning-label");
                    status.getStyleClass().add("success-label");
                }
            }

        });

        grid.add(submit, 1, 8);


        /////////////////////////////////////////////////////////////////////////////


        if(Operation.equals("Delete") || Operation.equals("Search") ){
            copies_tf.setDisable(true);
            media_title_tf.setDisable(true);
            album_rb.setDisable(true);
            game_rb.setDisable(true);
            movie_rb.setDisable(true);

            album_artist_tf.setDisable(true);
            album_songs_tf.setDisable(true);

            game_weight_tf.setDisable(true);

            movie_rating_tf.setDisable(true);

        }



        /////////////////////////////////////////////////////////////////////////////



        Button back_btn = new Button("Back");
        back_btn.getStyleClass().add("back-button");

        all.getChildren().add(back_btn);

        main.getChildren().add(grid);
        main.getChildren().add(illustration);

        all.getChildren().add(main);

        back_btn.setOnAction((event) -> {    // lambda expression
            Main.stage.getScene().setRoot(new MediaScreen());
        });

        this.getChildren().add(all);

        this.getStyleClass().add("light-bg");
    }
}

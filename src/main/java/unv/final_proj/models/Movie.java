package unv.final_proj.models;

import java.io.Serial;

public class Movie extends Media{

    @Serial
    private static final long serialVersionUID = 29987654987658965L;

    private String rating;

    public Movie(String Code, String title, int num_of_available_copies, String rating) {
        super(Code, title, num_of_available_copies);
        this.rating = rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie:\n\tcode: " + Code +
                "\n\ttitle: " + title +
                "\n\tcopies: " + num_of_available_copies +
                "\n\trating: " + rating + "\n";
    }
}

package unv.final_proj.models;

public class Movie extends Media{

    private String rating;

    public Movie(String Code, String title, int num_of_available_copies, String rating) {
        super(Code, title, num_of_available_copies);
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", num_of_available_copies=" + num_of_available_copies +
                ", rating='" + rating + '\'' +
                '}';
    }
}

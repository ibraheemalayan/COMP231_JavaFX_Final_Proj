package unv.final_proj.models;

public class Game extends Media{

    private double weight;

    public Game(String Code, String title, int num_of_available_copies, double weight) {
        super(Code, title, num_of_available_copies);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String
    toString() {
        return "Game{" +
                "weight=" + weight +
                ", title='" + title + '\'' +
                ", Code='" + Code + '\'' +
                ", num_of_available_copies=" + num_of_available_copies +
                '}';
    }
}

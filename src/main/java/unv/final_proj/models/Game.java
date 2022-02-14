package unv.final_proj.models;

import java.io.Serial;

public class Game extends Media{

    @Serial
    private static final long serialVersionUID = 28567023567235840L;

    private double weight;

    public Game(String Code, String title, int num_of_available_copies, double weight) {
        super(Code, title, num_of_available_copies);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String
    toString() {

        return "Game:\n\tcode: " + this.Code +
                "\n\ttitle: " + title +
                "\n\tcopies: " + num_of_available_copies +
                "\n\tweight: " + weight + "\n";

    }
}

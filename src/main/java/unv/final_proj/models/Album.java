package unv.final_proj.models;

import java.io.Serial;
import java.util.ArrayList;

public class Album extends Media{

    @Serial
    private static final long serialVersionUID = 13419741974986L;

    private String artist;
    private String songs;

    @Override
    public String toString() {

        return "Album:\n\tcode: " + Code +
                "\n\ttitle: " + title +
                "\n\tcopies: " + num_of_available_copies +
                "\n\tartist: " + artist +
                "\n\tsongs: " + songs + "\n";
    }

    public Album(String Code, String title, int num_of_available_copies, String artist, String songs) {
        super(Code, title, num_of_available_copies);
        this.artist = artist;
        this.songs = songs;
    }

    public String getArtist() {
        return artist;
    }

    public String getSongs() {
        return songs;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }
}

package unv.final_proj.models;

import java.util.ArrayList;

public class Album extends Media{

    private String artist;
    private String songs;

    @Override
    public String toString() {
        return "Album{" +
                "artist='" + artist + '\'' +
                ", songs='" + songs + '\'' +
                ", title='" + title + '\'' +
                ", Code='" + Code + '\'' +
                ", num_of_available_copies=" + num_of_available_copies +
                '}';
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

}

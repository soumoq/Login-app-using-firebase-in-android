package com.example.login_app_using_firebase;

public class Artist {
    String artist_Id;
    String artist_Name;
    String artist_type;

    public Artist(){}

    public Artist(String artist_Id, String artist_Name, String artist_type) {
        this.artist_Id = artist_Id;
        this.artist_Name = artist_Name;
        this.artist_type = artist_type;
    }

    public String getArtist_Id() {
        return artist_Id;
    }

    public String getArtist_Name() {
        return artist_Name;
    }

    public String getArtist_type() {
        return artist_type;
    }
}

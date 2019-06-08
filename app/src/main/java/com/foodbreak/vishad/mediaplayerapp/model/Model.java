package com.foodbreak.vishad.mediaplayerapp.model;

public class Model {

    int sng_id;
    String name;
    String artist;

    public Model(int sng_id, String name, String artist) {
        this.sng_id = sng_id;
        this.name = name;
        this.artist = artist;
    }
    public int getsng_id() {
        return sng_id;
    }

    public void setSng_id(int sng_id) {
        this.sng_id = sng_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

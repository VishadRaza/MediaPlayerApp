package com.foodbreak.vishad.mediaplayerapp.model;

import com.foodbreak.vishad.mediaplayerapp.R;

import java.util.ArrayList;

public class ListDetails {
    public static ArrayList<Model> getlist(){

        ArrayList<Model> list = new ArrayList<>();
        list.add(new Model(R.raw.piano, "name", "fenfewknvfs"));
        list.add(new Model(R.raw.so, "name", "fenfewknvfs"));
        list.add(new Model(R.raw.song, "name", "fenfewknvfs"));
        return  list;
    }

}

package com.example.heros;

import android.media.Image;
import android.widget.ImageView;

public class Hero {
    private String name;
    private String description;
    private String superpower;
    private String ranking;
    private ImageView image;
    public Hero(String name, String description, String superpower, String ranking, ImageView image){
        this.name = name;
        this.description = description;
        this.superpower = superpower;
        this.ranking = ranking;
        this.image = image;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getSuperpower(){
        return superpower;
    }
    public String getRanking(){
        return ranking;
    }
    public ImageView getImage(){
        return image;
    }
}

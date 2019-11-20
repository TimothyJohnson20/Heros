package com.example.heros;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Hero implements Parcelable {
    private String name;
    private String description;
    private String superpower;
    private String ranking;
    private String image;
    public Hero(String name, String description, String superpower, String ranking, String image){
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
    public String getImage(){
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.superpower);
        dest.writeString(this.ranking);
        dest.writeString(this.image);
    }

    protected Hero(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.superpower = in.readString();
        this.ranking = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel source) {
            return new Hero(source);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}

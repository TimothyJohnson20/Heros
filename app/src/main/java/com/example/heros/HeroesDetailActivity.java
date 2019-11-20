package com.example.heros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class HeroesDetailActivity extends AppCompatActivity {

    private ImageView imageView_heroImage;
    private TextView textView_heroName;
    private TextView textView_heroDescription;
    private TextView textView_heroSuperpower;
    private TextView textView_heroRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_detail);
        wireWidgets();
        Intent lastIntent = getIntent();
        Hero hero = lastIntent.getParcelableExtra(HeroesListActivity.HERO);
        textView_heroName.setText(hero.getName());
        textView_heroName.setTextSize(100);
        textView_heroDescription.setText("Description: " + hero.getDescription());
        textView_heroRanking.setText("Ranking: " + String.valueOf(hero.getRanking()) );
        textView_heroSuperpower.setText("Superpower: " + hero.getSuperpower());
        int resourceImage = getResources().getIdentifier(hero.getImage(), "drawable", getPackageName());
        imageView_heroImage.setImageDrawable(getResources().getDrawable(resourceImage));

    }

//    public String readTextFile(InputStream inputStream) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte buf[] = new byte[1024];
//        int len;
//        try {
//            while ((len = inputStream.read(buf)) != -1) {
//                outputStream.write(buf, 0, len);
//            }
//            outputStream.close();
//            inputStream.close();
//        } catch (IOException e) {
//        }
//        return outputStream.toString();
//    }

    public void wireWidgets(){
        imageView_heroImage = findViewById(R.id.imageView_heroes_image);
        textView_heroName = findViewById(R.id.textView_heroes_heroName);
        textView_heroDescription = findViewById(R.id.textView_heroes_heroDescription);
        textView_heroSuperpower = findViewById(R.id.textView_heroes_heroSuperPower);
        textView_heroRanking = findViewById(R.id.textView_heroes_heroRanking);
    }
}

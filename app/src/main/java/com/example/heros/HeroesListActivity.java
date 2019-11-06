package com.example.heros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


public class HeroesListActivity extends AppCompatActivity {

    //  Widgets
    ListView listViewHeros;
    //  Variables
    public List<Hero> heroesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);
        wireWidgets();
        InputStream JsonFileInput = getResources().openRawResource(R.raw.heroes); // getting XML
        String jsonfile = readTextFile(JsonFileInput);
        Gson gson = new Gson();
        Hero[] heroes = gson.fromJson(jsonfile, Hero[].class);
        heroesList = Arrays.asList(heroes);
        HeroAdapter heroAdapter = new HeroAdapter(heroesList);
        listViewHeros.setAdapter(heroAdapter);
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toString();
    }

    public void wireWidgets() {
        listViewHeros = findViewById(R.id.listView_heroesList_items);
    }


    private class HeroAdapter extends ArrayAdapter<Hero> {
        private List<Hero> heroesList;

        public HeroAdapter(List<Hero> heroesList) {

            super(HeroesListActivity.this, -1, heroesList);
            this.heroesList = heroesList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 1. inflate a layout
            LayoutInflater inflater = getLayoutInflater();

            // check if convertview is null, if so, replace it
            if (convertView == null) {
                // R.layout.item_hero is a custom layout we make that represents
                // what a single item would look like in our listview
                convertView = inflater.inflate(R.layout.item_hero, parent, false);
            }

            // 2. wire widgets & link the hero to those widgets
            // instead of calling findViewById at the activity class level,
            // we're calling it from the inflated layout to find THOSE widgets
            TextView textViewName = convertView.findViewById(R.id.textView_heroes_heroName);
            // do this for as many widgets as you need
            TextView ranking = convertView.findViewById(R.id.textView_theRower_number);
            TextView name = convertView.findViewById(R.id.textView_theRower_name);
            TextView description = convertView.findViewById(R.id.textView_theRower_desc);
            // set the values for each widget. use the position parameter variable
            // to get the hero that you need out of the list
            // and set the values for widgets.

            // 3. return inflated view
            return convertView;
        }

    }
}

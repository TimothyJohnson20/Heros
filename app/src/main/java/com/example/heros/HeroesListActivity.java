package com.example.heros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HeroesListActivity extends AppCompatActivity {

    //  Widgets
    ListView listViewHeros;
    //  Variables
    public List<Hero> heroesList;
    public static final String TAG = "HeroesListActivity";
    public static final String HERO = "Hero";
    private HeroAdapter heroAdapter;


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
        Log.d(TAG, "oncreate: " + heroes.toString());
        heroAdapter = new HeroAdapter(heroesList);
        listViewHeros.setAdapter(heroAdapter);
        setListeners();

    }
//      OPTIONS MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_heroeslist_sorting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_heroeslist_sort_by_name:
                sortByName();
                return true;
            case R.id.action_heroeslist_sort_by_rank:
                sortByRank();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void sortByName(){
        Toast.makeText(this, "Sort By Name", Toast.LENGTH_SHORT).show();
        Collections.sort(heroesList, new Comparator<Hero>() {
            @Override
            public int compare(Hero hero, Hero t1) {
                return hero.getName().toLowerCase().compareTo(t1.getName().toLowerCase());
            }
        });
        heroAdapter.notifyDataSetChanged();
    }

    private void sortByRank(){
        Toast.makeText(this, "Sort By Rank", Toast.LENGTH_SHORT).show();
        Collections.sort(heroesList, new Comparator<Hero>() {
            @Override
            public int compare(Hero hero, Hero t1) {
                return Integer.parseInt(hero.getRanking()) - Integer.parseInt(t1.getRanking());
            }
        });
        heroAdapter.notifyDataSetChanged();
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
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_hero, parent, false);
            }
            TextView textViewName = convertView.findViewById(R.id.textView_heroes_heroName);
            TextView ranking = convertView.findViewById(R.id.textView_theRower_number);
            TextView name = convertView.findViewById(R.id.textView_theRower_name);
            TextView description = convertView.findViewById(R.id.textView_theRower_desc);
            ranking.setText(heroesList.get(position).getRanking());
            name.setText(heroesList.get(position).getName());
            description.setText(heroesList.get(position).getDescription());
            return convertView;
        }

    }
    private void setListeners(){
        listViewHeros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hero hero = heroesList.get(i);
                Intent heroIntent = new Intent(HeroesListActivity.this, HeroesDetailActivity.class);
                heroIntent.putExtra(HERO, hero);
                startActivity(heroIntent);
                finish();
            }
        });
    }


}

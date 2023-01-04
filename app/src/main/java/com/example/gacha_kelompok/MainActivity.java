package com.example.gacha_kelompok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Item> data = Arrays.asList(
            new Item("Jamaludin Asyafaka", "jamaludin.asya@binus.ac.id", "2440724177"),
            new Item("Valen Setiawan","valen.setiawan@binus.ac.id", "2440862422"),
            new Item("Evlyn Sucipto","evlyn.sucipto@binus.ac.id", "2660070642"),
            new Item("Damar Amrullah","damar.amrullah@binus.ac.id", "2440278632"),
            new Item("Jessica Iska","jessica.iska@binus.ac.id", "2440090892"),
            new Item("Darrel Aska","darrel.aska@binus.ac.id", "2440088955"),
            new Item("Tubagus Julfikar","tubagus.julfikar@binus.ac.id", "2441080903"),
            new Item("Amarudin Akbar","amarudin.akbar@binus.ac.id", "2440084251"),
            new Item("Selina Nasution","selina.nasution@binus.ac.id", "2440055667"),
            new Item("Muhammad Akbar","muhammad.akbar@binus.ac.id", "2443120649"),
            new Item("Andres Solum","andres.solum@binus.ac.id", "2440089646")
    );
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Fadly Haikal", "fadly.haikal@binus.ac.id", "2440070642"));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.random).setOnClickListener(view -> {
            items.add(data.get(counter%data.size()));
            counter++;
            adapter.notifyItemInserted(items.size()-1);
        });

    }
}
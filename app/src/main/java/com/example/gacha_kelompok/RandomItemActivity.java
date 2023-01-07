package com.example.gacha_kelompok;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RandomItemActivity extends AppCompatActivity {
    List<Student> student_list;
    int counter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.item_main);
        final StudentDataService studentDataService = new StudentDataService(RandomItemActivity.this);
        counter = 0;
        setTitle("Random Student");

        List<Student> items = new ArrayList<Student>();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdpterRemove adapter = new AdpterRemove(items);
        recyclerView.setAdapter(adapter);

        studentDataService.getStudents(new StudentDataService.VolleyResponseListener() {
            @Override
            public void onResponse(List<Student> students) {
                student_list = students;
                findViewById(R.id.random).setOnClickListener(view -> {
                    items.add(student_list.get(counter%student_list.size()));
                    counter++;
                    adapter.notifyItemInserted(items.size()-1);}
                );
            }
            @Override
            public void onError(String message) {
                Toast.makeText(RandomItemActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

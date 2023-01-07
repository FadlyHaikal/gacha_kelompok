package com.example.gacha_kelompok;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayAllItemActivity extends AppCompatActivity {
    public Button random;
    public Button remove;
    List<Student> student_list;
    int counter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.item_main);
        final StudentDataService studentDataService = new StudentDataService(DisplayAllItemActivity.this);
        counter = 0;

        random = findViewById(R.id.random);
        random.setVisibility(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentDataService.getStudents(new StudentDataService.VolleyResponseListener() {
            @Override
            public void onResponse(List<Student> students) {
                student_list = students;
                Adapter adapter = new Adapter(students);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onError(String message) {
                Toast.makeText(DisplayAllItemActivity.this, message, Toast.LENGTH_SHORT).show();
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

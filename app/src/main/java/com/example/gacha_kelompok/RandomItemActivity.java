package com.example.gacha_kelompok;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RandomItemActivity extends AppCompatActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_main);

        RequestQueue queue = Volley.newRequestQueue(RandomItemActivity.this);
        String url ="https://63b6e4161907f863aa0598c7.mockapi.io/gachaKelompok/students";
        List<Student> student_list = new ArrayList<Student>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i < response.length(); i++){
                        JSONObject student = response.getJSONObject(i);
                        String id = student.getString("id");
                        String email = student.getString("email");
                        String phone = student.getString("phone");
                        String nim = student.getString("nim");
                        String firstName = student.getString("firstName");
                        String lastName = student.getString("lastName");
                        student_list.add(new Student(firstName + " " + lastName, email, nim, phone));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RandomItemActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

        List<Student> items = new ArrayList<Student>();
        items.add(new Student("Fadly Haikal", "fadly.haikal@binus.ac.id", "2440070642", "08248712452"));

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.random).setOnClickListener(view -> {
            items.add(student_list.get(counter%student_list.size()));
            counter++;
            adapter.notifyItemInserted(items.size()-1);}
        );
    }

}

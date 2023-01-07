package com.example.gacha_kelompok;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentDataService {
    public static final String endPoint = "https://63b6e4161907f863aa0598c7.mockapi.io/gachaKelompok/";

    Context ctx;

    public StudentDataService(Context context){
        this.ctx = context;
    }

    public interface VolleyResponseListener {
        void onResponse(List<Student> students);
        void onError(String message);
    }

    public void getStudents(final VolleyResponseListener volleyResponseListener){
        List<Student> students = new ArrayList<Student>();

        String url = endPoint + "students";

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
                        students.add(new Student(firstName + " " + lastName, email, nim, phone));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(students);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "Error Occured", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Error Occured");
            }
        });

        MySingleton.getInstance(ctx).addToRequestQueue(request);

    }
}

package com.belivit.mvvm.Repo;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.belivit.mvvm.Globals.GlobalData;
import com.belivit.mvvm.Model.User;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class MainActivityRepo {
    Application application;
    MutableLiveData<List<User>> userList;
    List<User> users;

    public MainActivityRepo(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<User>> getAllUser(){
        if (userList == null){
            userList = new MutableLiveData<>();
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, GlobalData.URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    Log.d("paul", "onResponse: " + response.get(0));
                    Gson gson = new Gson();
                    User user;
                    users = new ArrayList<>();
                    for (int i = 0 ; i < response.length() ; i++ ){
                        user = gson.fromJson(response.get(i).toString(), User.class);
                        users.add(user);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                userList.setValue(users);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(application);
        requestQueue.add(request);

        return userList;
    }
}

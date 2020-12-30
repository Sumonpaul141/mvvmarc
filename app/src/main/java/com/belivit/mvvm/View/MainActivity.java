package com.belivit.mvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.belivit.mvvm.Adapter.UserAdapter;
import com.belivit.mvvm.Model.User;
import com.belivit.mvvm.R;

import com.belivit.mvvm.ViewModel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private String TAG = "paul";
    private UserAdapter userAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getDataFromApi();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromApi();
                refreshLayout.setRefreshing(false);
            }
        });
        refreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark,R.color.colorAccent);

    }

    private void getDataFromApi() {
        mainActivityViewModel.getAllUsers(progressBar).observe(MainActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter = new UserAdapter(users, MainActivity.this);
                recyclerView.setAdapter(userAdapter);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        refreshLayout = findViewById(R.id.swipeRefresh);
    }
}

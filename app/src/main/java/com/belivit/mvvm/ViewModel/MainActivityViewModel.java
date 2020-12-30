package com.belivit.mvvm.ViewModel;

import android.app.Application;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.belivit.mvvm.Model.User;
import com.belivit.mvvm.Repo.MainActivityRepo;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MainActivityRepo mainActivityRepo;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mainActivityRepo = new MainActivityRepo(application);
    }

    public LiveData<List<User>> getAllUsers(ProgressBar progressBar){
        return mainActivityRepo.getAllUser(progressBar);
    }
}

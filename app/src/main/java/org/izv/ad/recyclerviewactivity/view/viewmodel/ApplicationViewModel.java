package org.izv.ad.recyclerviewactivity.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.izv.ad.recyclerviewactivity.model.Repository;

import java.util.ArrayList;

public class ApplicationViewModel extends AndroidViewModel {

    private Repository repository;

    public ApplicationViewModel(@NonNull @org.jetbrains.annotations.NotNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void addWord() {
        repository.addWord();
    }

    public LiveData<Integer> getElements() {
        return repository.getElements();
    }

    public ArrayList<String> getWords() {
        return repository.getWords();
    }
}
package org.izv.ad.recyclerviewactivity.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private ArrayList<String> words = new ArrayList<>();
    private Context context;
    private MutableLiveData<Integer> elements;

    public Repository(Context context) {
        this.context = context;
        elements = new MutableLiveData<>();
        addWordsThread(10);
    }

    public void addWord() {
        int threadsNumber = Runtime.getRuntime().availableProcessors();
        ExecutorService threadExecutor = Executors.newFixedThreadPool(threadsNumber);
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                words.add(0, generateWord());
                words.add(generateWord());
                elements.postValue(elements.getValue() + 2);
            }
        });
    }

    private void addWords(int words) {
        ArrayList<String> list = generateWords(words);
        ArrayList<String> previousList = readArrayList("list");
        if(previousList != null) {
            list.addAll(previousList);
        }
        saveArrayList(list, "list");
        this.words = list;
        elements.postValue(this.words.size());
    }

    private void addWordsThread(int words) {
        int threadsNumber = Runtime.getRuntime().availableProcessors();
        ExecutorService threadExecutor = Executors.newFixedThreadPool(threadsNumber);
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                addWords(words);
            }
        });
    }

    private String generateWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(8)+3];
        for(int j = 0; j < word.length; j++) {
            word[j] = (char)('a' + random.nextInt(26));
        }
        return new String(word);
    }

    private ArrayList<String> generateWords(int words) {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < words; i++) {
            list.add(generateWord());
        }
        return list;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public LiveData<Integer> getElements() {
        return elements;
    }

    private ArrayList<String> readArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.commit();
    }
}
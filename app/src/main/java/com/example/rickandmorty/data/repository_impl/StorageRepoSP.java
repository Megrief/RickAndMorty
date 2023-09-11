package com.example.rickandmorty.data.repository_impl;

import android.content.SharedPreferences;

import com.example.rickandmorty.domain.repository.StorageRepo;
import com.google.gson.Gson;

import javax.inject.Inject;

public class StorageRepoSP implements StorageRepo {

    Gson gson;
    SharedPreferences sharedPrefs;

    @Inject
    public StorageRepoSP(
            Gson gson,
            SharedPreferences sharedPrefs
    )  {
        this.gson = gson;
        this.sharedPrefs = sharedPrefs;
    }

    @Override
    public <T> void storeData(String key, T data) {
        String dataJsonString = gson.toJson(data);
        sharedPrefs.edit().putString(key, dataJsonString).apply();
    }

    @Override
    public <T> T getData(String key, Class<T> typeOfT) {
        String dataJsonString = sharedPrefs.getString(key, null);
        return dataJsonString != null ? gson.fromJson(dataJsonString, typeOfT) : null;
    }
}

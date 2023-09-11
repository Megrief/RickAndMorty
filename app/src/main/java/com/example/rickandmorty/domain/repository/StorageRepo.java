package com.example.rickandmorty.domain.repository;

public interface StorageRepo {

    <T> void storeData(String key, T data);

    <T> T getData(String key, Class<T> typeOfT);
}

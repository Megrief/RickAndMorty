package com.example.rickandmorty.domain.use_cases;

import com.example.rickandmorty.domain.repository.StorageRepo;

import javax.inject.Inject;

public final class StoreDataUseCase {

    StorageRepo repository;

    @Inject
    public StoreDataUseCase(
            StorageRepo repository
    ) {
        this.repository = repository;
    }

    public <T> void storeData(String key, T data) {
        repository.storeData(key, data);
    }
}

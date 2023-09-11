package com.example.rickandmorty.domain.use_cases;

import com.example.rickandmorty.domain.repository.StorageRepo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.inject.Inject;

public class GetDataUseCase {

    StorageRepo repository;

    @Inject
    public GetDataUseCase(
            StorageRepo repository
    ) {
        this.repository = repository;
    }

    ExecutorService executor = Executors.newCachedThreadPool();
    public <T> void getData(String key, Class<T> typeOfT, Consumer<T> consumer) {
        executor.execute(() ->
            consumer.accept(
                repository.getData(key, typeOfT)
            )
        );
    }
}

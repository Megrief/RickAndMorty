package com.example.rickandmorty.domain.use_cases;

import com.example.rickandmorty.domain.entities.Data;
import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.domain.repository.SearchRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.inject.Inject;

public class GetPageUseCase {
    SearchRepository repository;

    @Inject
    public GetPageUseCase(
            SearchRepository repository
    ) {
        this.repository = repository;
    }

    public <T extends Data> void getPage(String url, Consumer<Page> consumer) {
        ExecutorService executor = Executors.newCachedThreadPool();
//        executor.execute(() -> consumer.accept(repository.getPage(url)));
    }
}

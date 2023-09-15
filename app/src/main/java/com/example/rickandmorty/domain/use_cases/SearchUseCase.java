package com.example.rickandmorty.domain.use_cases;

import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.domain.repository.SearchRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.inject.Inject;

public final class SearchUseCase {
    private final SearchRepository repository;
    private final ExecutorService executors = Executors.newCachedThreadPool();

    @Inject
    public SearchUseCase(
        SearchRepository repository
    ) {
        this.repository = repository;
    }

    public void doSearch(String name, TypeOfData type, Consumer<Page> consumer) {
        executors.execute(() ->
            consumer.accept(
                repository.search(name, type)
            )
        );
    }
}

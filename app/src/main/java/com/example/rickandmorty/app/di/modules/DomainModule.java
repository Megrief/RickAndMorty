package com.example.rickandmorty.app.di.modules;


import com.example.rickandmorty.domain.repository.SearchRepository;
import com.example.rickandmorty.domain.repository.StorageRepo;
import com.example.rickandmorty.domain.use_cases.GetDataUseCase;
import com.example.rickandmorty.domain.use_cases.GetPageUseCase;
import com.example.rickandmorty.domain.use_cases.SearchUseCase;
import com.example.rickandmorty.domain.use_cases.StoreDataUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public class DomainModule {

    @Provides
    public SearchUseCase provideSearchUseCase(SearchRepository searchRepository) {
        return new SearchUseCase(searchRepository);
    }

    @Provides
    public GetPageUseCase providesGetPageUseCase(SearchRepository searchRepository) {
        return new GetPageUseCase(searchRepository);
    }

    @Provides
    public GetDataUseCase provideGetDataUseCase(StorageRepo storageRepo) {
        return new GetDataUseCase(storageRepo);
    }

    @Provides
    public StoreDataUseCase provideStoreDataUseCase(StorageRepo storageRepo) {
        return new StoreDataUseCase(storageRepo);
    }
}

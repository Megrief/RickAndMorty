package com.example.rickandmorty.app.di.modules;

import android.os.Handler;
import android.os.Looper;

import com.example.rickandmorty.data.network.NetworkClient;
import com.example.rickandmorty.data.network.RetrofitNetworkClient;
import com.example.rickandmorty.data.network.rick_and_morty_service.RickAndMortyService;
import com.example.rickandmorty.data.repository_impl.SearchRepositoryImpl;
import com.example.rickandmorty.domain.repository.SearchRepository;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {

    @Provides
    @Singleton
    public SearchRepository provideSearchRepository(NetworkClient networkClient, Gson gson) {
        return new SearchRepositoryImpl(networkClient, gson);
    }

    @Provides
    @Singleton
    public NetworkClient provideNetworkClient(RickAndMortyService apiService) {
        return new RetrofitNetworkClient(apiService);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public static RickAndMortyService provideService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RickAndMortyService.class);
    }

    @Provides
    @Singleton
    public static Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }
}

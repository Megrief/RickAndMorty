package com.example.rickandmorty.app.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.example.rickandmorty.data.network.NetworkClient;
import com.example.rickandmorty.data.network.RetrofitNetworkClient;
import com.example.rickandmorty.data.network.rick_and_morty_service.RickAndMortyService;
import com.example.rickandmorty.data.repository_impl.SearchRepositoryImpl;
import com.example.rickandmorty.data.repository_impl.StorageRepoSP;
import com.example.rickandmorty.domain.repository.SearchRepository;
import com.example.rickandmorty.domain.repository.StorageRepo;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
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
    public StorageRepo provideStorageRepo(SharedPreferences sharedPrefs, Gson gson) {
        return new StorageRepoSP(gson, sharedPrefs);
    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    public SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences("RICK_AND_MORTY", Context.MODE_PRIVATE);
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
    public static Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }
}

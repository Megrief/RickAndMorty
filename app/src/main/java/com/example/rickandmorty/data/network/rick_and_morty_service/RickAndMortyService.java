package com.example.rickandmorty.data.network.rick_and_morty_service;

import com.example.rickandmorty.data.network.response.CharacterResponse;
import com.example.rickandmorty.data.network.response.EpisodeResponse;
import com.example.rickandmorty.data.network.response.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickAndMortyService {
    @GET("character/?")
    Call<CharacterResponse> searchCharacter(@Query("name") String name);

    @GET("location/?")
    Call<LocationResponse> searchLocation(@Query("name") String name);

    @GET("episode/?")
    Call<EpisodeResponse> searchEpisode(@Query("name") String name);


//    @GET("/{type}?")
//    Call<ApiResponse> getPage(@Path("type") String type, @Query("page") String page, @Query("name") String name);
}

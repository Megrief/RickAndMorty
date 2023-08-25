package com.example.rickandmorty.data.network.rick_and_morty_service;

import com.example.rickandmorty.data.network.response.ApiResponse;
import com.example.rickandmorty.data.network.response.SuccessResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RickAndMortyService {
    @GET("{type}/?")
    Call<SuccessResponse> search(@Path("type") String type, @Query("name") String name);

//    @GET("/{type}?")
//    Call<ApiResponse> getPage(@Path("type") String type, @Query("page") String page, @Query("name") String name);
}

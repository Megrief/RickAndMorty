package com.example.rickandmorty.data.network;

import android.util.Log;

import com.example.rickandmorty.data.network.response.ApiResponse;
import com.example.rickandmorty.data.network.rick_and_morty_service.RickAndMortyService;
import com.google.gson.JsonSyntaxException;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetworkClient implements NetworkClient {
    RickAndMortyService apiService;
//    String baseUrl = "https://rickandmortyapi.com/api";

//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();

//    RickAndMortyService apiService = retrofit.create(RickAndMortyService.class);
    @Inject
    public RetrofitNetworkClient(
        RickAndMortyService apiService
    ) {
        this.apiService = apiService;
    }
    @Override
    public ApiResponse doRequest(String type, String name) {

        Response<? extends ApiResponse> response = null;


        if (apiService != null) {
            Log.e("AAA", "ApiService not null");
            try {
                response = apiService.search(type, name).execute();

            } catch (IOException e) {
                Log.e("No answer", "Server not response");
            }
        }

        if (response != null) {
            ApiResponse result = response.body() != null ? response.body() : new ApiResponse();
            result.setResponseCode(response.code());
            return response.body();
        }


        Log.e("AAA", "Response is Null");
        return null;
    }

//    @Override
//    public ApiResponse doRequest(String type, String name, String page) {
//        Response<ApiResponse> response = null;
//
//        try {
//            response = apiService.getPage(type, page, name).execute();
//
//        } catch (IOException e) {
//            Log.e("No answer", "Server not response");
//        }
//
//        if (response == null || response.body() == null) {
//            return new ApiResponse(ApiResponse.FAILURE);
//        } else {
//            return response.body();
//        }
//
//
//    }
}

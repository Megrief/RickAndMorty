package com.example.rickandmorty.data.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.rickandmorty.data.network.response.ApiResponse;
import com.example.rickandmorty.data.network.rick_and_morty_service.RickAndMortyService;
import com.example.rickandmorty.domain.entities.TypeOfData;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public final class RetrofitNetworkClient implements NetworkClient {
    RickAndMortyService apiService;

    @Inject
    public RetrofitNetworkClient(
        RickAndMortyService apiService
    ) {
        this.apiService = apiService;
    }

    @NonNull
    @Override
    public ApiResponse doRequest(TypeOfData type, String name) {
        Response<? extends ApiResponse> response;
        try {
            switch (type) {
                case CHARACTER: {
                    response = apiService.searchCharacter(name).execute();
                    break;
                }
                case LOCATION: {
                    response = apiService.searchLocation(name).execute();
                    Log.e("AAA", "success");
                    break;
                }
                case EPISODE: {
                    response = apiService.searchEpisode(name).execute();
                    break;
                }
                default:
                    response = null;
            }

        } catch (IOException e) {
            Log.e("No answer", "Server is not responding");
            return new ApiResponse().setResponseCode(ApiResponse.NO_INTERNET);
        }

        if (response != null) {
            ApiResponse result = response.body() != null ? response.body() : new ApiResponse();
            result.setResponseCode(response.code());
            return response.body();
        }


        Log.e("AAA", "Response is Null");
        return new ApiResponse().setResponseCode(ApiResponse.FAILURE);
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

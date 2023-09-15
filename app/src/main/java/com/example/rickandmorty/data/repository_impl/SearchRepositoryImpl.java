package com.example.rickandmorty.data.repository_impl;

import android.util.Log;

import com.example.rickandmorty.data.network.NetworkClient;
import com.example.rickandmorty.data.network.response.ApiResponse;
import com.example.rickandmorty.data.network.response.CharacterResponse;
import com.example.rickandmorty.data.network.response.EpisodeResponse;
import com.example.rickandmorty.data.network.response.Info;
import com.example.rickandmorty.data.network.response.LocationResponse;
import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.domain.repository.SearchRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchRepositoryImpl implements SearchRepository {

    private final NetworkClient networkClient;

    @Inject
    public SearchRepositoryImpl(
        NetworkClient networkClient
    ) {
        this.networkClient = networkClient;
    }

    @Override
    public Page search(String name, TypeOfData type) {
        Log.e("AAA", "In repository");
        ApiResponse response = networkClient.doRequest(type, name);

        switch (response.getResponseCode()) {
            case ApiResponse.SUCCESS: {
                Log.e("AAA", "Response code is success");
                try {
                    return onSuccess(response, type);
                } catch (Exception e) {
                    Log.e("AAA", "Can't cast response");

                    return new Page(
                            null,
                            null,
                            new ArrayList<>()
                    );
                }
            }
            case ApiResponse.FAILURE: {
                Log.e("AAA", "Response code is failure");
                return null;
            }
            case ApiResponse.NO_INTERNET: {
                Log.e("AAA", "Internet connection error");
                return null;
            }
            default: {
                Log.e("AAA", "Unknown response code");
                return null;
            }
        }
    }

    private Page onSuccess(ApiResponse response, TypeOfData type) {

        switch (type) {
            case CHARACTER: {
                if (response instanceof CharacterResponse) {
                    Info info = ((CharacterResponse) response).getInfo();
                    if (info != null) {
                        Log.e("AAA", "info is not null");

                        return new Page(
                                info.getPrevious(),
                                info.getNext(),
                                ((CharacterResponse) response).getResults()
                        );
                    }
                }
                break;
            }

            case LOCATION: {
                if (response instanceof LocationResponse) {
                    Info info = ((LocationResponse) response).getInfo();
                    if (info != null) {
                        Log.e("AAA", "info is not null");

                        return new Page(
                                info.getPrevious(),
                                info.getNext(),
                                ((LocationResponse) response).getResults()
                        );
                    }
                }
                break;
            }

            case EPISODE: {
                if (response instanceof EpisodeResponse) {
                    Info info = ((EpisodeResponse) response).getInfo();
                    if (info != null) {
                        Log.e("AAA", "info is not null");

                        return new Page(
                                info.getPrevious(),
                                info.getNext(),
                                ((EpisodeResponse) response).getResults()
                        );
                    }
                }
                break;
            }
        }

        Log.e("AAA", "Info is null");

        return new Page(
                null,
                null,
                new ArrayList<>()
        );
    }
}

package com.example.rickandmorty.data.repository_impl;

import android.util.Log;

import com.example.rickandmorty.data.network.NetworkClient;
import com.example.rickandmorty.data.network.response.ApiResponse;
import com.example.rickandmorty.data.network.response.Info;
import com.example.rickandmorty.data.network.response.SuccessResponse;
import com.example.rickandmorty.domain.entities.data.CharacterData;
import com.example.rickandmorty.domain.entities.data.Data;
import com.example.rickandmorty.domain.entities.Page;
import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.domain.repository.SearchRepository;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchRepositoryImpl implements SearchRepository {

    private final NetworkClient networkClient;
    private final Gson gson;

    @Inject
    public SearchRepositoryImpl(
        NetworkClient networkClient,
        Gson gson
    ) {
        this.networkClient = networkClient;
        this.gson = gson;
    }

    @Override
    public Page search(String name, TypeOfData type) {
        Log.e("AAA", "In repository");
        ApiResponse response = networkClient.doRequest(type.getType(), name);

        if (response != null) {
            Log.e("AAA", "Response in repository not Null");
            if (response.getResponseCode() == ApiResponse.SUCCESS) {
                Log.e("AAA", "Response code is success");
                try {
                    SuccessResponse successResponse = (SuccessResponse) response;
                    Info info = successResponse.getInfo();

                    if (info != null) {
                        Log.e("AAA", "info is not null");

                        ArrayList<CharacterData> results = successResponse.getResults();
                        if (!results.isEmpty()) {
                            Log.e("AAA", results.get(0).getName());
                        }
                        return new Page(
                                info.getPrevious(),
                                info.getNext(),
                                results,
                                type
                        );
                    } else {
                        Log.e("AAA", "Info is null");

                        return new Page(
                                null,
                                null,
                                new ArrayList<>(),
                                type
                        );
                    }
                } catch (Exception e) {
                    Log.e("AAA", "Can't transform ApiResponse to SuccessResponse");
                    return null;
                }

            }
        }

        Log.e("AAA", "Response is null, repository");
        return null;

    }

//    @Override
//    @NotNull
//    public Page getPage(String url) {
//
//        ApiResponse response = networkClient.doRequest();
//        String[] results = response.getResults();
//
//        return new Page(
//                response.getInfo().getPrevious(),
//                response.getInfo().getNext(),
//                getData(results, type.getTypeOfData()),
//                type
//        );
//    }

    private <T extends Data> ArrayList<T> getData(ArrayList<String> jsonArray, Class<T> typeOfT) {
        ArrayList<T> resultingList = new ArrayList<>();

        for (String s : jsonArray) {
            resultingList.add(gson.fromJson(s, typeOfT));
        }

        return resultingList;
    }
}

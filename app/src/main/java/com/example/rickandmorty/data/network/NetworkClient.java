package com.example.rickandmorty.data.network;

import com.example.rickandmorty.data.network.response.ApiResponse;
import com.example.rickandmorty.domain.entities.TypeOfData;

public interface NetworkClient {
    ApiResponse doRequest(TypeOfData type, String name);

//    ApiResponse doRequest(String type, String name, String page);
}

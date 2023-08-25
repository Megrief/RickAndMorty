package com.example.rickandmorty.data.network;

import com.example.rickandmorty.data.network.response.ApiResponse;

public interface NetworkClient {
    ApiResponse doRequest(String type, String name);

//    ApiResponse doRequest(String type, String name, String page);
}

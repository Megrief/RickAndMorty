package com.example.rickandmorty.data.network.response;

public class ApiResponse {


    public ApiResponse() { }
    int responseCode = 0;

    public static final int NO_INTERNET = 500;
    public static final int FAILURE = 400;
    public static final int SUCCESS = 200;

    public ApiResponse setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }


    public int getResponseCode() {
        return responseCode;
    }
}

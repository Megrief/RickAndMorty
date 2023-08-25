package com.example.rickandmorty.data.network.response;

import android.util.Log;

import com.example.rickandmorty.domain.entities.CharacterData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SuccessResponse extends ApiResponse {

    @SerializedName("info")
    Info info;
    @SerializedName("results")
    ArrayList<CharacterData> results;

    public SuccessResponse(
            Info info,
           ArrayList<CharacterData> results
    ) {
        this.info = info;
        Log.e("AAA", "Info serialized successfully");
        this.results = results;
        Log.e("AAA", "Results serialized successfully");
    }

    public ArrayList<CharacterData> getResults() {
        return results;
    }

    public Info getInfo() {
        return info;
    }
}

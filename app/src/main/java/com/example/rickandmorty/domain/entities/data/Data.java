package com.example.rickandmorty.domain.entities.data;

import com.example.rickandmorty.domain.entities.TypeOfData;
import com.google.gson.annotations.SerializedName;

public class Data {
    final TypeOfData typeOfData;

    @SerializedName("id")
    int id;

    public Data(
            TypeOfData typeOfData,
            int id
    ) {
        this.typeOfData = typeOfData;
        this.id = id;
    }

    public TypeOfData getTypeOfData() {
        return typeOfData;
    }

    public int getId() {
        return id;
    }
}

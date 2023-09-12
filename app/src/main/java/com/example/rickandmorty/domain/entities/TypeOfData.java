package com.example.rickandmorty.domain.entities;

import com.example.rickandmorty.domain.entities.data.CharacterData;
import com.example.rickandmorty.domain.entities.data.Data;
import com.example.rickandmorty.domain.entities.data.EpisodeData;
import com.example.rickandmorty.domain.entities.data.LocationData;

public enum TypeOfData {
    CHARACTER("character", CharacterData.class),
    LOCATION("location", LocationData.class),
    EPISODE("episode", EpisodeData.class);

    final String type;
    final Class<? extends Data> typeOfData;

    TypeOfData(
            String type,
            Class<? extends Data> typeOfData
    ) {
        this.type = type;
        this.typeOfData = typeOfData;
    }

    public String getType() {
        return type;
    }

    public Class<? extends Data> getTypeOfData() {
        return typeOfData;
    }
}
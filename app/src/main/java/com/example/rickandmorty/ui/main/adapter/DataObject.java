package com.example.rickandmorty.ui.main.adapter;

import com.example.rickandmorty.domain.entities.TypeOfData;

public class DataObject {
    private final String name;
    private final String firstPropertyValue;
    private final String secondPropertyValue;

    final TypeOfData type;
    private final String url;

    public DataObject(
            String name,
            String firstPropertyValue,
            String secondPropertyValue,
            TypeOfData type,
            String url
    ) {
        this.name = name;
        this.firstPropertyValue = firstPropertyValue;
        this.secondPropertyValue = secondPropertyValue;
        this.type = type;
        this.url = url;
    }

    public String getName() {
        return name;
    }
    public TypeOfData getType() {
        return type;
    }

    public String getFirstPropertyValue() {
        return firstPropertyValue;
    }
    public String getSecondPropertyValue() {
        return secondPropertyValue;
    }
    public String getUrl() {
        return url;
    }
}

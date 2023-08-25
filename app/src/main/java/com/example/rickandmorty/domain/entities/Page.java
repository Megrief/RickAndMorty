package com.example.rickandmorty.domain.entities;

import java.util.ArrayList;

public class Page {
    final String previousUrl;
    final String nextUrl;
    final ArrayList<? extends Data> data;
    final TypeOfData type;


    public Page(
            String previousUrl,
            String nextUrl,
            ArrayList<? extends Data> data,
            TypeOfData type
    ) {
        this.previousUrl = previousUrl;
        this.nextUrl = nextUrl;
        this.data = data;
        this.type = type;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public ArrayList<? extends Data> getData() {
        return data;
    }

    public TypeOfData getTypeOfData() {
        return type;
    }
}

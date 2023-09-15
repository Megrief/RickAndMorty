package com.example.rickandmorty.ui.fragments.ui_state;

import com.example.rickandmorty.domain.entities.data.Data;

import java.util.ArrayList;
import java.util.List;

public class ShowData implements ScreenState {
    final List<Data> data = new ArrayList<>();

    public ShowData(
            List<? extends Data> data
    ) {
        this.data.addAll(data);
    }

    public List<Data> getData() {

        return data;
    }

}

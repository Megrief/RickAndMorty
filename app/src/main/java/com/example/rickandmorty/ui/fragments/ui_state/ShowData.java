package com.example.rickandmorty.ui.fragments.ui_state;

import com.example.rickandmorty.ui.fragments.adapters.rv.DataObject;

import java.util.ArrayList;

public class ShowData implements ScreenState {
    ArrayList<DataObject> data;

    public ShowData(
            ArrayList<DataObject> data
    ) {
        this.data = data;
    }


    public void setContent(ArrayList<DataObject> newData) {
        data.clear();
        data.addAll(newData);
    }

    public ArrayList<DataObject> getData() {
        return data;
    }

}

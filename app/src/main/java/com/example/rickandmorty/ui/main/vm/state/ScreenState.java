package com.example.rickandmorty.ui.main.vm.state;

import com.example.rickandmorty.domain.entities.TypeOfData;
import com.example.rickandmorty.ui.main.adapter.DataObject;

import java.util.ArrayList;

public class ScreenState {
    public static class Loading extends ScreenState { }

    public static class Empty extends ScreenState { }

    public static class ShowData extends ScreenState {
        private final ArrayList<DataObject> data;

        public ShowData(
                ArrayList<DataObject> data
        ) {
            this.data = data;
        }

        public ArrayList<DataObject> getData() {
            return data;
        }

    }
}

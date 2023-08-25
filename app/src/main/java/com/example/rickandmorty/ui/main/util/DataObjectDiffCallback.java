package com.example.rickandmorty.ui.main.util;

import androidx.recyclerview.widget.DiffUtil;

import com.example.rickandmorty.ui.main.adapter.DataObject;

import java.util.ArrayList;

public class DataObjectDiffCallback extends DiffUtil.Callback {

    ArrayList<DataObject> oldList;
    ArrayList<DataObject> newList;

    public DataObjectDiffCallback(
            ArrayList<DataObject> oldList,
            ArrayList<DataObject> newList
    ) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        DataObject oldItem = oldList.get(oldItemPosition);
        DataObject newItem = newList.get(newItemPosition);
        return oldItem.getUrl().equals(newItem.getUrl());
    }
}

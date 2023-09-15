package com.example.rickandmorty.ui.fragments.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.rickandmorty.domain.entities.data.Data;

public class DataObjectDiffCallback extends DiffUtil.ItemCallback<Data> {

    public DataObjectDiffCallback() { }

    @Override
    public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
        return oldItem.getTypeOfData().equals(newItem.getTypeOfData())
                && oldItem.getId() == newItem.getId();
    }
}

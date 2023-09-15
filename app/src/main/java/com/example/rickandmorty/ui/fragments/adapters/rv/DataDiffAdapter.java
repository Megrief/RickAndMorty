package com.example.rickandmorty.ui.fragments.adapters.rv;

import android.util.Log;

import com.example.rickandmorty.domain.entities.data.Data;
import com.example.rickandmorty.ui.fragments.util.DataObjectDiffCallback;
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter;

public class DataDiffAdapter extends AsyncListDifferDelegationAdapter<Data> {

    public DataDiffAdapter() {
        super(new DataObjectDiffCallback());

        delegatesManager
                .addDelegate(
                    new CharacterAdapterDelegate(v ->
                        Log.e("Click!", "Item was clicked")
                    )
                )
                .addDelegate(
                    new LocationAdapterDelegate(v ->
                        Log.e("Click!", "Item was clicked")
                    )
                )
                .addDelegate(
                    new EpisodeAdapterDelegate(v ->
                        Log.e("Click!", "Item was clicked")
                    )
                );
    }
}

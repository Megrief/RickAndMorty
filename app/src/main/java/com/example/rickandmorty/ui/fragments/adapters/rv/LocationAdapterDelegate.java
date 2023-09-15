package com.example.rickandmorty.ui.fragments.adapters.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.TextCardBinding;
import com.example.rickandmorty.domain.entities.data.CharacterData;
import com.example.rickandmorty.domain.entities.data.Data;
import com.example.rickandmorty.domain.entities.data.LocationData;
import com.example.rickandmorty.ui.fragments.util.OnCardClick;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class LocationAdapterDelegate extends AbsListItemAdapterDelegate<LocationData, Data, LocationAdapterDelegate.LocationVH> {

    OnCardClick onCardClick;
    public LocationAdapterDelegate(
            OnCardClick onCardClick
    ) {
        this.onCardClick = onCardClick;
    }
    @Override
    protected boolean isForViewType(@NonNull Data item, @NonNull List<Data> items, int position) {
        return item instanceof CharacterData;
    }

    @NonNull
    @Override
    protected LocationVH onCreateViewHolder(@NonNull ViewGroup parent) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new LocationVH(TextCardBinding.inflate(inflater, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull LocationData item, @NonNull LocationVH holder, @NonNull List<Object> payloads) {
        holder.bind(item);
        holder.itemView.setOnClickListener(v -> onCardClick.onClick(item.getUrl()));
    }

    static class LocationVH extends RecyclerView.ViewHolder {
        TextCardBinding binding;

        public LocationVH(
                @NonNull TextCardBinding binding
        ) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(LocationData item) {
            binding.nameValue.setText(item.getName());
            binding.firstProp.setText(R.string.species);
            binding.firstPropValue.setText(item.getType());
            binding.secondProp.setText(R.string.dimention);
            binding.secondPropValue.setText(item.getDimension());
        }
    }
}

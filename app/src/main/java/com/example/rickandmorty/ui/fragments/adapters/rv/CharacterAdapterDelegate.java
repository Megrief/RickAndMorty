package com.example.rickandmorty.ui.fragments.adapters.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.CharacterCardBinding;
import com.example.rickandmorty.domain.entities.data.CharacterData;
import com.example.rickandmorty.domain.entities.data.Data;
import com.example.rickandmorty.ui.fragments.util.OnCardClick;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class CharacterAdapterDelegate extends AbsListItemAdapterDelegate<CharacterData, Data, CharacterAdapterDelegate.CharacterVH> {
    private final OnCardClick onCardClick;

    public CharacterAdapterDelegate(OnCardClick onCardClick) {
        this.onCardClick = onCardClick;
    }

    @Override
    protected boolean isForViewType(@NonNull Data item, @NonNull List<Data> items, int position) {
        return item instanceof CharacterData;
    }

    @NonNull
    @Override
    protected CharacterVH onCreateViewHolder(@NonNull ViewGroup parent) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new CharacterVH(CharacterCardBinding.inflate(inflater, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull CharacterData item, @NonNull CharacterVH holder, @NonNull List<Object> payloads) {
        holder.bind(item);
        holder.itemView.setOnClickListener(v -> onCardClick.onClick(item.getUrl()));
    }

    static class CharacterVH extends RecyclerView.ViewHolder {
        CharacterCardBinding binding;

        CharacterVH(@NonNull CharacterCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CharacterData data) {
            binding.nameValue.setText(data.getName());
            binding.firstProp.setText(R.string.species);
            binding.firstPropValue.setText(data.getSpecies());
            binding.secondProp.setText(R.string.status);
            binding.secondPropValue.setText(data.getStatus());
            Glide.with(binding.getRoot()).load(data.getImage()).into(binding.image);
        }
    }
}

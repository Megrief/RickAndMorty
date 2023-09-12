package com.example.rickandmorty.ui.fragments.adapters.rv;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.CharacterCardBinding;

public class DataViewHolder extends RecyclerView.ViewHolder {
    CharacterCardBinding binding;

    DataViewHolder(@NonNull CharacterCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(DataObject data) {
        binding.nameValue.setText(data.getName());
        binding.firstPropValue.setText(data.getFirstPropertyValue());
        binding.secondPropValue.setText(data.getSecondPropertyValue());

        switch (data.getType()) {
            case CHARACTER: {
                binding.firstProp.setText(R.string.species);
                binding.secondProp.setText(R.string.status);
                Glide.with(binding.getRoot()).load(data.getImageUrl()).into(binding.image);
                break;
            }
            case LOCATION: {
                binding.firstProp.setText(R.string.species);
                binding.secondProp.setText(R.string.dimention);
                break;
            }
            case EPISODE: {
                binding.firstProp.setText(R.string.air_date);
                binding.secondProp.setText(R.string.episode_code);
                break;
            }
        }
    }

}

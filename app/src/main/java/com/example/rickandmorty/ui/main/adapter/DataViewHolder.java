package com.example.rickandmorty.ui.main.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.DataCardBinding;

public class DataViewHolder extends RecyclerView.ViewHolder {
    DataCardBinding binding;

    DataViewHolder(DataCardBinding binding) {
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

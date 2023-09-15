package com.example.rickandmorty.ui.fragments.adapters.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.R;
import com.example.rickandmorty.databinding.TextCardBinding;
import com.example.rickandmorty.domain.entities.data.Data;
import com.example.rickandmorty.domain.entities.data.EpisodeData;
import com.example.rickandmorty.ui.fragments.util.OnCardClick;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

import java.util.List;

public class EpisodeAdapterDelegate extends AbsListItemAdapterDelegate<EpisodeData, Data, EpisodeAdapterDelegate.EpisodeVH> {

    OnCardClick onCardClick;
    public EpisodeAdapterDelegate(
            OnCardClick onCardClick
    ) {
        this.onCardClick = onCardClick;
    }

    @Override
    protected boolean isForViewType(@NonNull Data item, @NonNull List<Data> items, int position) {
        return item instanceof EpisodeData;
    }

    @NonNull
    @Override
    protected EpisodeVH onCreateViewHolder(@NonNull ViewGroup parent) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new EpisodeVH(TextCardBinding.inflate(inflater, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull EpisodeData item, @NonNull EpisodeVH holder, @NonNull List<Object> payloads) {
        holder.bind(item);
        holder.itemView.setOnClickListener(v -> onCardClick.onClick(item.getUrl()));
    }

    static class EpisodeVH extends RecyclerView.ViewHolder {

        TextCardBinding binding;
        public EpisodeVH(
                @NonNull TextCardBinding binding
        ) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(EpisodeData item) {
            binding.nameValue.setText(item.getName());
            binding.firstProp.setText(R.string.episode_code);
            binding.firstPropValue.setText(item.getEpisodeCode());
            binding.secondProp.setText(R.string.air_date);
            binding.secondPropValue.setText(item.getAirDate());
        }
    }
}

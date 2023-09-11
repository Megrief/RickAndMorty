package com.example.rickandmorty.ui.fragments.adapters.rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmorty.databinding.DataCardBinding;
import com.example.rickandmorty.ui.fragments.util.DataObjectDiffCallback;
import com.example.rickandmorty.ui.fragments.util.OnCardClick;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {
    private final ArrayList<DataObject> dataList = new ArrayList<>();
    private final OnCardClick onCardClick;

    public DataAdapter(OnCardClick onCardClick) {
        this.onCardClick = onCardClick;
    }

    public void setDataList(ArrayList<DataObject> newList) {
        DataObjectDiffCallback diffCallback = new DataObjectDiffCallback(dataList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        dataList.clear();
        dataList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new DataViewHolder(DataCardBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DataObject data = dataList.get(position);
        holder.bind(data);
        holder.itemView.setOnClickListener(v -> onCardClick.onClick(data.getUrl()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}

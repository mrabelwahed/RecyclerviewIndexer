package com.ramadan.indexer;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Data> dataList;

    DataAdapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getIndex() != null ? 0 : 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return getItemViewType(position) == 0 ?
                new IndexViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.index_item, viewGroup ,false)) :
                new DataViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Data data = dataList.get(position);
        if (getItemViewType(position) == 0) {
            IndexViewHolder viewHolder = (IndexViewHolder) holder;
            viewHolder.tvIndex.setText(data.getIndex());
        } else {
            DataViewHolder viewHolder = (DataViewHolder) holder;
            viewHolder.tvName.setText(data.getName());
            viewHolder.tvAge.setText("(" + data.getAge() + " years old)");
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

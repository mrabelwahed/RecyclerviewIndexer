package com.ramadan.indexer;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class DataViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvAge;

    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvAge = itemView.findViewById(R.id.tvAge);
    }
}
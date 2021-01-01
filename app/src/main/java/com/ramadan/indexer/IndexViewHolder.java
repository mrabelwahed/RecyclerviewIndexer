package com.ramadan.indexer;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class IndexViewHolder extends RecyclerView.ViewHolder {
    public TextView tvIndex;
    public IndexViewHolder(View inflate) {
        super(inflate);
        tvIndex=inflate.findViewById(R.id.tvIndex);
    }
}
package com.example.gacha_kelompok;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView nameView, nimView, emailView;
    private MyAdapter adapter;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        nimView = itemView.findViewById(R.id.nim);
        emailView = itemView.findViewById(R.id.email);
        itemView.findViewById(R.id.remove).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public MyViewHolder linkAdapter(MyAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}

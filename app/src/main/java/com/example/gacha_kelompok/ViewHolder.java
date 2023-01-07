package com.example.gacha_kelompok;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ViewHolder extends RecyclerView.ViewHolder{

    TextView nameView, nimView, emailView;
    private Adapter adapter;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        nimView = itemView.findViewById(R.id.nim);
        emailView = itemView.findViewById(R.id.email);
        itemView.findViewById(R.id.remove).setVisibility(View.GONE);
    }

    public ViewHolder linkAdapter(Adapter adapter){
        this.adapter = adapter;
        return this;
    }
}

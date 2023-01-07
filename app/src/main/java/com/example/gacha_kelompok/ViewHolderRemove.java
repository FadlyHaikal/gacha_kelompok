package com.example.gacha_kelompok;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ViewHolderRemove extends RecyclerView.ViewHolder{

    TextView nameView, nimView, emailView;
    private AdpterRemove adapter;

    public ViewHolderRemove(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        nimView = itemView.findViewById(R.id.nim);
        emailView = itemView.findViewById(R.id.email);
        itemView.findViewById(R.id.remove).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public ViewHolderRemove linkAdapter(AdpterRemove adapter){
        this.adapter = adapter;
        return this;
    }
}

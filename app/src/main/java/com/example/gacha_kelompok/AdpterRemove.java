package com.example.gacha_kelompok;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdpterRemove extends RecyclerView.Adapter<ViewHolderRemove>{
    List<Student> items;

    public AdpterRemove(List<Student> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolderRemove onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolderRemove(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRemove holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.nimView.setText(items.get(position).getNim());
        holder.emailView.setText(items.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

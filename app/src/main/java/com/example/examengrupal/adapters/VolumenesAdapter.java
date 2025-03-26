package com.example.examengrupal.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examengrupal.ArticlesActivity;
import com.example.examengrupal.R;
import com.example.examengrupal.models.Volumen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VolumenesAdapter extends RecyclerView.Adapter<VolumenesAdapter.VolumenViewHolder> {

    private List<Volumen> volumenes;

    public VolumenesAdapter(List<Volumen> volumenes) {
        this.volumenes = volumenes;
    }

    @NonNull
    @Override
    public VolumenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_volumen, parent, false);
        return new VolumenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolumenViewHolder holder, int position) {
        Volumen volumen = volumenes.get(position);
        holder.tvTitulo.setText(volumen.getTitle());
        holder.tvYear.setText(volumen.getYear());
        Picasso.get().load(volumen.getCover()).into(holder.ivCover);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ArticlesActivity.class);
            intent.putExtra("issue_id", volumen.getIssueId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return volumenes.size();
    }

    public static class VolumenViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitulo, tvYear;

        public VolumenViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvYear = itemView.findViewById(R.id.tvYear);
        }
    }
}

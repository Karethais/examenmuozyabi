package com.example.examengrupal.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examengrupal.RevistaDetailActivity;
import com.example.examengrupal.R;
import com.example.examengrupal.models.Revista;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RevistasAdapter extends RecyclerView.Adapter<RevistasAdapter.RevistaViewHolder> {

    private List<Revista> revistas;
    private Context context;

    public RevistasAdapter(List<Revista> revistas, Context context) {
        this.revistas = revistas;
        this.context = context;
    }

    @NonNull
    @Override
    public RevistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_revista, parent, false);
        return new RevistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RevistaViewHolder holder, int position) {
        Revista revista = revistas.get(position);
        holder.tvNombre.setText(revista.getName());

        String description = revista.getDescription();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.tvDescripcion.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.tvDescripcion.setText(Html.fromHtml(description));
        }
        Picasso.get().load(revista.getPortada()).into(holder.ivPortada);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RevistaDetailActivity.class);
            intent.putExtra("journal_id", revista.getJournal_id());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return revistas.size();
    }

    public static class RevistaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPortada;
        TextView tvNombre, tvDescripcion;

        public RevistaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPortada = itemView.findViewById(R.id.ivPortada);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
}

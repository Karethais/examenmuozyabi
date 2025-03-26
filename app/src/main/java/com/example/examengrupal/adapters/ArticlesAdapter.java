package com.example.examengrupal.adapters;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examengrupal.R;
import com.example.examengrupal.models.Article;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<Article> articles;

    public ArticlesAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvDoi.setText(article.getDoi());
        holder.tvAbstract.setText(Html.fromHtml(article.getAbstractText(), Html.FROM_HTML_MODE_COMPACT));
        holder.tvDate.setText(article.getDatePublished());

        holder.btnPdf.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getPdfUrl()));
            v.getContext().startActivity(intent);
        });
        holder.btnHtml.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getHtmlUrl()));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDoi, tvAbstract, tvDate;
        Button btnPdf, btnHtml;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDoi = itemView.findViewById(R.id.tvDoi);
            tvAbstract = itemView.findViewById(R.id.tvAbstract);
            tvDate = itemView.findViewById(R.id.tvDate);
            btnPdf = itemView.findViewById(R.id.btnPdf);
            btnHtml = itemView.findViewById(R.id.btnHtml);
        }
    }
}

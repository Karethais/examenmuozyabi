package com.example.examenmuozyabi;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenmuozyabi.classs.ARTICULO;
import com.example.examenmuozyabi.servciosder.ARTICULOSERV;
import com.example.examenmuozyabi.classs.ARTICULO;
import com.example.examenmuozyabi.servciosder.ARTICULOSERV;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticuloActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticuloAdap adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String issueId = getIntent().getStringExtra("issue_id");
        fetchArticles(issueId);
    }

    private void fetchArticles(String issueId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ARTICULOSERV service = retrofit.create(ARTICULOSERV.class);
        Call<List<ARTICULO>> call = service.getArticles(issueId);

        call.enqueue(new Callback<List<ARTICULOSERV>>() {
            @Override
            public void onResponse(Call<List<ARTICULO>> call, Response<List<ARTICULO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ARTICULO> articleList = response.body();
                    adapter = new ArticuloAdap(articleList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("ArticleActivity", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<ARTICULO>> call, Throwable t) {
                Log.e("ArticleActivity", "Failed to fetch articles", t);
            }
        });
    }
}
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

import com.example.examenmuozyabi.classs.JOURN;
import com.example.examenmuozyabi.servciosder.JOURSERV;
import com.example.examenmuozyabi.classs.JOURN;
import com.example.examenmuozyabi.servciosder.JOURSERV;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JournalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        fetchJournals();
    }

    private void fetchJournals() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JOURSERV service = retrofit.create(JOURSERV.class);
        Call<List<JOURN>> call = service.getJournals();

        call.enqueue(new Callback<List<JOURN>>() {
            @Override
            public void onResponse(Call<List<JOURN>> call, Response<List<JOURN>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<JOURN> journalList = response.body();
                    adapter = new JournalAdapter(journalList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("MainActivity", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<JOURN>> call, Throwable t) {
                Log.e("MainActivity", "Failed to fetch journals", t);
            }
        });
    }
}
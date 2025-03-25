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

import com.example.examenmuozyabi.classs.VOLUME;
import com.example.examenmuozyabi.servciosder.VOLUMESERV;
import com.example.examenmuozyabi.classs.VOLUME;
import com.example.examenmuozyabi.servciosder.VOLUMESERV;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VolumenActovity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolumenAdap adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String journalId = getIntent().getStringExtra("journal_id");
        fetchVolumes(journalId);
    }

    private void fetchVolumes(String journalId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VOLUMESERV service = retrofit.create(VOLUMESERV.class);
        Call<List<VOLUME>> call = service.getVolumes(journalId);

        call.enqueue(new Callback<List<VOLUME>>() {
            @Override
            public void onResponse(Call<List<VOLUME>> call, Response<List<VOLUME>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<VOLUME> volumeList = response.body();
                    adapter = new VolumenAdap(volumeList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("VolumeActivity", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<VOLUME>> call, Throwable t) {
                Log.e("VolumeActivity", "Failed to fetch volumes", t);
            }
        });
    }
}
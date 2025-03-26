package com.example.examengrupal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examengrupal.adapters.RevistasAdapter;
import com.example.examengrupal.models.Revista;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RevistasAdapter adapter;
    private List<Revista> revistaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Habilitar edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Aplicar insets al contenedor principal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new FetchRevistasTask().execute();
    }

    private class FetchRevistasTask extends AsyncTask<Void, Void, List<Revista>> {
        @Override
        protected List<Revista> doInBackground(Void... voids) {
            List<Revista> revistas = new ArrayList<>();
            try {
                URL url = new URL("https://revistas.uteq.edu.ec/ws/journals.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                StringBuilder sb = new StringBuilder();
                int data = reader.read();
                while (data != -1) {
                    sb.append((char) data);
                    data = reader.read();
                }

                JSONArray jsonArray = new JSONArray(sb.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String journal_id = obj.getString("journal_id");
                    String portada = obj.getString("portada");
                    String abbreviation = obj.getString("abbreviation");
                    String description = obj.getString("description");
                    String journalThumbnail = obj.getString("journalThumbnail");
                    String name = obj.getString("name");

                    Revista revista = new Revista(journal_id, portada, abbreviation, description, journalThumbnail, name);
                    revistas.add(revista);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return revistas;
        }

        @Override
        protected void onPostExecute(List<Revista> revistas) {
            if (!revistas.isEmpty()) {
                adapter = new RevistasAdapter(revistas, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}

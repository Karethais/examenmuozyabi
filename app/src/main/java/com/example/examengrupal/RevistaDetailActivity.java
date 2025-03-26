package com.example.examengrupal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examengrupal.adapters.VolumenesAdapter;
import com.example.examengrupal.models.Volumen;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RevistaDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VolumenesAdapter adapter;
    private String journal_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revista_detail);

        recyclerView = findViewById(R.id.recyclerViewVolumenes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        journal_id = getIntent().getStringExtra("journal_id");
        new FetchVolumenesTask().execute(journal_id);
    }

    private class FetchVolumenesTask extends AsyncTask<String, Void, List<Volumen>> {
        @Override
        protected List<Volumen> doInBackground(String... params) {
            String journalId = params[0];
            List<Volumen> volumenes = new ArrayList<>();
            try {
                URL url = new URL("https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + journalId);
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
                    String issueId = obj.getString("issue_id");
                    String volume = obj.getString("volume");
                    String number = obj.getString("number");
                    String year = obj.getString("year");
                    String title = obj.getString("title");
                    String doi = obj.getString("doi");
                    String cover = obj.getString("cover");

                    Volumen v = new Volumen(issueId, volume, number, year, title, doi, cover);
                    volumenes.add(v);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return volumenes;
        }

        @Override
        protected void onPostExecute(List<Volumen> volumenes) {
            if (!volumenes.isEmpty()) {
                adapter = new VolumenesAdapter(volumenes);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(RevistaDetailActivity.this, "No se encontraron ediciones", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

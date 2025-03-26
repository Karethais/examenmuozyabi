package com.example.examengrupal;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examengrupal.adapters.ArticlesAdapter;
import com.example.examengrupal.models.Article;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArticlesAdapter adapter;
    private String issueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        recyclerView = findViewById(R.id.recyclerViewArticles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        issueId = getIntent().getStringExtra("issue_id");
        new FetchArticlesTask().execute(issueId);
    }

    private class FetchArticlesTask extends AsyncTask<String, Void, List<Article>> {
        @Override
        protected List<Article> doInBackground(String... params) {
            String issueId = params[0];
            List<Article> articles = new ArrayList<>();
            try {
                URL url = new URL("https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + issueId);
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
                    String publicationId = obj.getString("publication_id");
                    String title = obj.getString("title");
                    String doi = obj.getString("doi");
                    String abstractText = obj.getString("abstract");
                    String datePublished = obj.getString("date_published");

                    // Extraer URLs de galeys
                    String pdfUrl = "";
                    String htmlUrl = "";
                    JSONArray galeys = obj.getJSONArray("galeys");
                    for (int j = 0; j < galeys.length(); j++) {
                        JSONObject gObj = galeys.getJSONObject(j);
                        String label = gObj.getString("label");
                        if (label.equalsIgnoreCase("PDF")) {
                            pdfUrl = gObj.getString("UrlViewGalley");
                        } else if (label.equalsIgnoreCase("HTML")) {
                            htmlUrl = gObj.getString("UrlViewGalley");
                        }
                    }
                    Article article = new Article(publicationId, title, doi, abstractText, datePublished, pdfUrl, htmlUrl);
                    articles.add(article);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return articles;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            if (!articles.isEmpty()) {
                adapter = new ArticlesAdapter(articles);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(ArticlesActivity.this, "No se encontraron artículos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

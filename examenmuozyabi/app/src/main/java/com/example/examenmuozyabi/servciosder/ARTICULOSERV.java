package com.example.examenmuozyabi.servciosder;

import com.example.examenmuozyabi.ARTICULO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ARTICULOSERV {
    @GET("ws/pubs.php")
    Call<List<ARTICULO>> getArticles(@Query("i_id") String issueId);
}

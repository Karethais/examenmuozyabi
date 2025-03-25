package com.example.examenmuozyabi.servciosder;

import com.example.examenmuozyabi.classs.JOURN;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JOURSERV {
    @GET("ws/journals.php")
    public Call<List<JOURN>> getJournals();
}

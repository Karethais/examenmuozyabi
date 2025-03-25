package com.example.examenmuozyabi.servciosder;
import com.example.examenmuozyabi.VOLUME;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VOLUMESERV {
    @GET("ws/issues.php")
    Call<List<VOLUME>> getVolumes(@Query("j_id") String journalId);


}

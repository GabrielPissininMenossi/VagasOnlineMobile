package com.unoeste.vagasonlinemobile.interfaces;

import com.unoeste.vagasonlinemobile.entities.Vaga;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VagasService
{
    @GET("get-all")
    Call<List<Vaga>> getAll();
    @GET("get-one/{registro}")
    Call<Vaga> getOne(@Path("registro") String registro);
}

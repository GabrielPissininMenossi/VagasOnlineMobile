package com.unoeste.vagasonlinemobile.configs;

import com.unoeste.vagasonlinemobile.interfaces.VagasService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitVagasConfig
{
    private final Retrofit retrofit;

    public RetrofitVagasConfig()
    {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.15.6:8080/apis/vagas/").
                addConverterFactory(GsonConverterFactory.create()).build();
    }
    public VagasService getVagasService()
    {
        return this.retrofit.create(VagasService.class);
    }
}

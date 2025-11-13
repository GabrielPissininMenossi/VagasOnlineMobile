package com.unoeste.vagasonlinemobile.configs;

import com.unoeste.vagasonlinemobile.interfaces.InteressesService;
import com.unoeste.vagasonlinemobile.interfaces.VagasService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInteressesConfig
{
    private final Retrofit retrofit;

    public RetrofitInteressesConfig()
    {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.15.6:8080/apis/").
                addConverterFactory(GsonConverterFactory.create()).build();
    }
    public InteressesService getInteressesService()
    {
        return this.retrofit.create(InteressesService.class);
    }
}

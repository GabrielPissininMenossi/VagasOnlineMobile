package com.unoeste.vagasonlinemobile.interfaces;

import com.unoeste.vagasonlinemobile.entities.Interesses;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InteressesService
{
    @POST("interesse")
    Call<Interesses> addInteresse(@Body Interesses interesses);

}

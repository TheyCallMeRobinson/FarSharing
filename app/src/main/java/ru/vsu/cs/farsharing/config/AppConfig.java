package ru.vsu.cs.farsharing.config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class AppConfig {

    public static final String baseUrl = "http://farsharing-server.herokuapp.com";

    public static final Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(baseUrl)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .client(new OkHttpClient.Builder().build())
                                            .build();
}
package com.android.example.gadsleaderboard.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitBuilder {
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";

    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = sBuilder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}

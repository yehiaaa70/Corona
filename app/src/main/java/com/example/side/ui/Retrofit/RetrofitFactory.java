package com.example.side.ui.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private final static String BASE_URL = "https://young-lowlands-78267.herokuapp.com/api/";
    private static Retrofit retrofit;


    public static Retrofit getRetrofit() {

        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

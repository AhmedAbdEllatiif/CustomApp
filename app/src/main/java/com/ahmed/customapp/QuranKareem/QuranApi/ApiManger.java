package com.ahmed.customapp.QuranKareem.QuranApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManger {


    private static Retrofit retrofit;


    private static Retrofit getInstance(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://mp3quran.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            /*return retrofit = new Retrofit
                    .Builder()
                    .baseUrl("https://zawia.app/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();*/
        }
        return retrofit;
    }

    public static Services getServices(){
        return getInstance().create(Services.class);
    }
}
package com.example.neslaram.testspotify.service;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {

    public static final String API_BASE_URL = "https://api.spotify.com/v1/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    private static SpotifyClient API_SERVICE;

    public static SpotifyClient getApiService() {

        if (API_SERVICE == null) {

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    //In order to convert the API response type Observable, we have to set the call adapter to RxJavaCallAdapter.
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient());

            Retrofit retrofit = builder.client(httpClient.build()).build();
            API_SERVICE = retrofit.create(SpotifyClient.class);
        }

        return API_SERVICE;

    }
//
//    private static Retrofit.Builder builder =
//            new Retrofit.Builder()
//                    .baseUrl(API_BASE_URL)
//                    .client(new OkHttpClient())
//                    .addConverterFactory(GsonConverterFactory.create());

//    public static <S> S createService(Class<S> serviceClass) {
//        Retrofit retrofit = builder.client(httpClient.build()).build();
//        return retrofit.create(serviceClass);
//    }
}
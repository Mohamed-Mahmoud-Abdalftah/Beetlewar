package com.beetleware.task.data.network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.beetleware.task.data.network.Constant.BASE_URL;

/**
 * Created by Ahmad Shubita on 8/1/17.
 */

public class ApiFactory {
    private static Retrofit retrofit = null;




    private static OkHttpClient getHttpLoggingInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES).addInterceptor(interceptor).build();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return client;

    }


    public static UsersService createApi() {

        Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(getHttpLoggingInterceptor())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();


        return retrofit.create(UsersService.class);
    }


}

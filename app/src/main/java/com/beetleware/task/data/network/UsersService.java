package com.beetleware.task.data.network;

import com.beetleware.task.ui.login.requestLogin;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface UsersService {


    @Headers({"Accept: application/json","Content-Type:  application/json"})
    @POST("auth")
    Observable<Response<ResponseBody>> auth(@Body requestLogin request);

    @Headers({"Accept: application/json","Content-Type:  application/json"})
    @GET("sold-items")
    Observable<Response<ResponseBody>> sold_items(@Header("Authorization")String auth);

    @Headers({"Accept: application/json","Content-Type:  application/json"})
    @GET("products")
    Observable<Response<ResponseBody>> products(@Header("Authorization")String auth);

    @Headers({"Accept: application/json","Content-Type:  application/json"})
    @GET("profile-show")
    Observable<Response<ResponseBody>> profile_show(@Header("Authorization")String auth);
}

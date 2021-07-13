package com.example.side.ui.Retrofit;


import com.example.side.ui.home.CommentModel;
import com.example.side.ui.home.UserModel;
import com.example.side.ui.home.criticalModel;
import com.example.side.ui.qr.QRCodeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServices {

    @GET("qr_code/{id}")
    Call<List<QRCodeModel>> QR_Code(@Path("id") String id);

    @PUT("qr_code/update/{id}")
    Call<QRCodeModel> updateNumPatient(@Path("id") String id, @Body QRCodeModel model);


    @POST("qr_code")
    Call<QRCodeModel> CreateQR(@Body QRCodeModel model);


    @GET("posts")
    Call<List<criticalModel>> getPosts();

    @POST("posts")
    Call<criticalModel> createPost(@Body criticalModel model);

//    @PUT("posts/update/{id}")
//    Call<criticalModel> updateWaring(@Path("id") String id, @Body criticalModel model);
//

    @GET("users/{id}")
    Call<UserModel> getUser(@Path("id") String id);

    @POST("comment")
    Call<CommentModel> createComment(@Body CommentModel model);





}

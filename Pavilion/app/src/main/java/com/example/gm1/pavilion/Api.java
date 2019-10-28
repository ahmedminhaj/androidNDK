package com.example.gm1.pavilion;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST(value = "auth/user_registration")
    Call<ResponseBody> createUser(
            @Field("role_id") int role_id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password,
            @Field("android_id") String android_id
    );
}

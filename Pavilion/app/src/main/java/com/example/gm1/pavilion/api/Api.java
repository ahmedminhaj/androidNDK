package com.example.gm1.pavilion.api;

import com.example.gm1.pavilion.models.AttendanceResponse;
import com.example.gm1.pavilion.models.CateringResponse;
import com.example.gm1.pavilion.models.EntryExitResponse;
import com.example.gm1.pavilion.models.SignInRespose;

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
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("android_id") String android_id

    );

    @FormUrlEncoded
    @POST(value = "auth/user_login")
    Call<SignInRespose> userSignIn(
            @Field("email") String email,
            @Field("password") String password,
            @Field("role_id") int role_id
    );

    @FormUrlEncoded
    @POST(value = "user/user_entry_time")
    Call<EntryExitResponse> entryTime(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST(value = "user/user_exit_time")
    Call<EntryExitResponse> exitTime(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST(value = "user/user_time_check")
    Call<EntryExitResponse> timeChecker(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST(value = "user/user_timing_list")
    Call<AttendanceResponse> timingList(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST(value = "catering/user_catering_order_create")
    Call<ResponseBody> saveOrder(
            @Field("user_id") int user_id,
            @Field("date") String date,
            @Field("comment") String comment
    );

    @FormUrlEncoded
    @POST(value = "catering/user_catering_list")
    Call<CateringResponse> cateringList(
            @Field("user_id") int user_id
    );

    @FormUrlEncoded
    @POST(value = "catering/user_catering_order_delete")
    Call<CateringResponse> deleteMeal(
            @Field("id") int id
    );
}

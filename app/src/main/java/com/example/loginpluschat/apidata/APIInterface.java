package com.example.loginpluschat.apidata;

import com.example.loginpluschat.utility.Constant;
import com.example.loginpluschat.model.MainModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @GET(Constant.EndPointConstants.GET)
    Call<MainModel> getUsersList();

    @GET(Constant.EndPointConstants.GET_USER_CHAT)
    Call<MainModel> getUserChat(@Path("id") String id);

    @POST(Constant.EndPointConstants.SEND_MSG_TO_USER)
    @FormUrlEncoded
    Call<MainModel> sendMsgToUser(@Field("id") String id, @Field("message") String message);
}

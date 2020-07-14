package com.example.loginpluschat.ui.presenter;

import androidx.annotation.NonNull;

import com.example.loginpluschat.apidata.APIInterface;
import com.example.loginpluschat.apidata.ApiClient;
import com.example.loginpluschat.model.MainModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeInteractor implements IHomeContract.IHomeInteractor {

    @Override
    public void fetchData(OnFinishListener onFinishListener) {
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<MainModel> call = apiInterface.getUsersList();
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(@NonNull Call<MainModel> call, @NonNull Response<MainModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getUsers() != null) {
                        onFinishListener.onFinished(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MainModel> call, @NonNull Throwable t) {
                onFinishListener.onFail(t);
            }
        });
    }
}
package com.example.loginpluschat.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpluschat.R;
import com.example.loginpluschat.apidata.APIInterface;
import com.example.loginpluschat.apidata.ApiClient;
import com.example.loginpluschat.interfaces.IItemClickListener;
import com.example.loginpluschat.model.MainModel;
import com.example.loginpluschat.model.ResponseModel;
import com.example.loginpluschat.ui.adapter.HomeAdapter;
import com.example.loginpluschat.utility.Constant;
import com.example.loginpluschat.utility.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements IItemClickListener {
    private RecyclerView mRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intiView();
        fetchUsersList();
    }

    private void fetchUsersList() {
        Utils.showProgressDialog(this);

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<MainModel> call = apiInterface.getUsersList();
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(@NonNull Call<MainModel> call, @NonNull Response<MainModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getUsers() != null) {
                        setAdapter(response.body().getUsers());
                    }
                }
                Utils.hideProgressDialog();
            }

            @Override
            public void onFailure(@NonNull Call<MainModel> call, @NonNull Throwable t) {
                Utils.toastMessage(HomeActivity.this, getString(R.string.try_again_later));
                Utils.hideProgressDialog();
            }
        });
    }

    private void setAdapter(List<ResponseModel> usersInfoList) {
        HomeAdapter adapter = new HomeAdapter(this, usersInfoList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRv.setLayoutManager(mLayoutManager);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setAdapter(adapter);
    }

    private void intiView() {
        mRv = findViewById(R.id.rv_home);
    }

    @Override
    public void onItemClick(String id) {
        Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
        intent.putExtra(Constant.ID, id);
        startActivity(intent);
    }
}
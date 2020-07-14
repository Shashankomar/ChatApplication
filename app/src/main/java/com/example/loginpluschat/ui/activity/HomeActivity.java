package com.example.loginpluschat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpluschat.R;
import com.example.loginpluschat.interfaces.IItemClickListener;
import com.example.loginpluschat.model.ResponseModel;
import com.example.loginpluschat.ui.adapter.HomeAdapter;
import com.example.loginpluschat.ui.presenter.HomeInteractor;
import com.example.loginpluschat.ui.presenter.HomePresenter;
import com.example.loginpluschat.ui.presenter.IHomeContract;
import com.example.loginpluschat.utility.Constant;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements IItemClickListener, IHomeContract.IHomeView {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private RecyclerView mRv;
    private IHomeContract.IHomePresenter homePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intiView();
        homePresenter = new HomePresenter(this, new HomeInteractor());
        homePresenter.requestDataFromServer();
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

    @Override
    public void setListInAdapter(List<ResponseModel> users) {
        setAdapter(users);
    }

    @Override
    public void setErrorMessage(String message) {

    }

    @Override
    public void setLog(String s) {
        Log.e(TAG, s);
    }
}
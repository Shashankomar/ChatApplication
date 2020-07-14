package com.example.loginpluschat.ui.presenter;

import com.example.loginpluschat.model.MainModel;

public class HomePresenter implements IHomeContract.IHomePresenter, IHomeContract.IHomeInteractor.OnFinishListener {

    IHomeContract.IHomeInteractor homeInteractor;
    private IHomeContract.IHomeView iHomeView;

    public HomePresenter(IHomeContract.IHomeView iHomeView, HomeInteractor homeInteractor) {
        this.iHomeView = iHomeView;
        this.homeInteractor = homeInteractor;
    }

    @Override
    public void onFinished(MainModel body) {
        if (body != null) {
            iHomeView.setLog(body.getMessage());
            iHomeView.setListInAdapter(body.getUsers());
        } else {
            String message = "sklslflskdfljskdfkjlsj";
            iHomeView.setErrorMessage(message);
        }
    }

    @Override
    public void onFail(Throwable t) {
        iHomeView.setLog(t.toString());
    }

    @Override
    public void requestDataFromServer() {
        homeInteractor.fetchData(this);
    }
}
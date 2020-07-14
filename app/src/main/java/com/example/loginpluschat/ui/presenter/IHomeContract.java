package com.example.loginpluschat.ui.presenter;

import com.example.loginpluschat.model.MainModel;
import com.example.loginpluschat.model.ResponseModel;

import java.util.List;

/*
 * HOME CONTRACTOR IS USED TO CREATE ONE TO ONE RELATION SHIP BETWEEN VIEW PRESENTER OF ONE ACTIVITY ONLY.
 * */
public interface IHomeContract {
    /*
    presenter to have the logical part of home activity or view.
     */
    interface IHomePresenter {
        void requestDataFromServer();
    }

    /*
    View interface to set data in a Home activity or perform some
    activity task like show/ hide loader etc.
    */
    interface IHomeView {
        void setListInAdapter(List<ResponseModel> users);
        void setErrorMessage(String message);
        void setLog(String s);
    }

    /*
    intractor interface is used to have api responses and data to set in
    recyclerview this sets success and failure callbacks
    */
    interface IHomeInteractor {

        interface OnFinishListener {
            void onFinished(MainModel body);
            void onFail(Throwable t);
        }

        void fetchData(OnFinishListener onFinishListener);
    }
}

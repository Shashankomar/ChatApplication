package com.example.loginpluschat.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpluschat.R;
import com.example.loginpluschat.apidata.APIInterface;
import com.example.loginpluschat.apidata.ApiClient;
import com.example.loginpluschat.model.MainModel;
import com.example.loginpluschat.model.ResponseModel;
import com.example.loginpluschat.ui.adapter.ChatAdapter;
import com.example.loginpluschat.utility.Constant;
import com.example.loginpluschat.utility.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private EditText mEtChat;
    private Button mSendBtn;
    private ChatAdapter mAdapter;
    private String mUserId;
    private String mMessage;
    private ImageView mIvBack;
    private TextView mTitleName;
    private ImageView mUserIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getData();
        initView();
        setListeners();
        fetchUserChat();
    }

    private void getData() {
        if (getIntent().hasExtra(Constant.ID)) {
            mUserId = getIntent().getStringExtra(Constant.ID);
        }
    }

    private void fetchUserChat() {
        Utils.showProgressDialog(this);
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<MainModel> call = apiInterface.getUserChat(mUserId);
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(@NonNull Call<MainModel> call, @NonNull Response<MainModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setToolbar(response.body());
                        setRecyclerView(response.body().getChats());
                    }
                }
                Utils.hideProgressDialog();
            }

            @Override
            public void onFailure(@NonNull Call<MainModel> call, @NonNull Throwable t) {
                Utils.toastMessage(ChatActivity.this, getString(R.string.try_again_later));
                Utils.hideProgressDialog();
            }
        });
    }

    private void setToolbar(MainModel response) {
        String image = response.getUser().getImage();
        mTitleName.setText(response.getUser().getName());
        if (image != null && !image.isEmpty()) {
            Glide.with(ChatActivity.this).load(image).error(R.drawable.ic_launcher_foreground).into(mUserIv);
        } else {
            Glide.with(ChatActivity.this).load(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(mUserIv);
        }
    }

    private void setRecyclerView(List<ResponseModel> chats) {
        for (ResponseModel responseModel : chats) {
            responseModel.setNewItemAdded(false);
        }
        mAdapter = new ChatAdapter(this, chats);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setStackFromEnd(true);
        mRv.setLayoutManager(mLayoutManager);
        mRv.setAdapter(mAdapter);
    }

    private void setListeners() {
        mIvBack.setOnClickListener(view -> onBackPressed());

        mSendBtn.setOnClickListener(v -> onSendMsg());
    }

    private void onSendMsg() {
        mMessage = mEtChat.getText().toString();
        if (textValidation()) {
            String dateTime = Utils.getDateTime("yyyy-MM-dd hh:mm:ss");
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage(mMessage);
            responseModel.setUserId(Integer.parseInt(mUserId));
            responseModel.setCreatedAt(dateTime);
            responseModel.setNewItemAdded(true);
            mAdapter.insertListItem(responseModel);
            mRv.scrollToPosition(mAdapter.getItemCount() - 1);
            mEtChat.getText().clear();
            apiSendNewMessage();
        }
    }

    private boolean textValidation() {
        if (mMessage.trim().isEmpty()) {
            Utils.toastMessage(this, getString(R.string.please_type_msg));
            return false;
        } else
            return true;
    }

    private void apiSendNewMessage() {
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<MainModel> call = apiInterface.sendMsgToUser(mUserId, mMessage);
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(@NonNull Call<MainModel> call, @NonNull Response<MainModel> response) {
                if (response.body() != null) {
                    ResponseModel chat = response.body().getChat();
                    chat.setNewItemAdded(false);
                    mAdapter.updateListItem(chat);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MainModel> call, @NonNull Throwable t) {
                Utils.toastMessage(ChatActivity.this, getString(R.string.try_again_later));
            }
        });
    }

    private void initView() {
        mRv = findViewById(R.id.rv_msg_list);
        mEtChat = findViewById(R.id.et_chat_box);
        mSendBtn = findViewById(R.id.btn_send_chat_box);
        mIvBack = findViewById(R.id.iv_tb_back);
        mTitleName = findViewById(R.id.tv_tb_chat);
        mUserIv = findViewById(R.id.iv_tb_chat);
    }
}
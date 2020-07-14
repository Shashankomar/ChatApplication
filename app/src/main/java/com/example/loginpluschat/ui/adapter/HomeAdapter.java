package com.example.loginpluschat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpluschat.R;
import com.example.loginpluschat.interfaces.IItemClickListener;
import com.example.loginpluschat.model.ResponseModel;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    private final List<ResponseModel> mUsersList;
    private IItemClickListener mListener;
    private Context mContext;

    public HomeAdapter(Context context, List<ResponseModel> usersList, IItemClickListener listener) {
        mContext = context;
        mUsersList = usersList;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_list_item, parent, false);

        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeViewHolder homeViewHolder = (HomeViewHolder) holder;
        ResponseModel userModel = mUsersList.get(position);
        homeViewHolder.tvName.setText(userModel.getName());
//        homeViewHolder.tvName.setText("U+209X");

        if (userModel.getImage() != null && !userModel.getImage().isEmpty()) {
            Glide.with(mContext)
                    .load(userModel.getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(homeViewHolder.ivCard);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.ic_launcher_background)
                    .into(homeViewHolder.ivCard);
        }
    }

    @Override
    public int getItemCount() {
        return mUsersList != null ? mUsersList.size() : 0;
    }

    private class HomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCard;
        private TextView tvName;

        HomeViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            ivCard = itemView.findViewById(R.id.iv_card);
            RelativeLayout rlClick = itemView.findViewById(R.id.rl_click);

            rlClick.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onItemClick(mUsersList.get(getAdapterPosition()).getId().toString());
                }
            });
        }
    }
}

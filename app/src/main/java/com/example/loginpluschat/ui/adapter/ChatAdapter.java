package com.example.loginpluschat.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpluschat.R;
import com.example.loginpluschat.model.ResponseModel;
import com.example.loginpluschat.utility.Utils;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {

    private static final int MESSAGE_RECEIVE = 1;
    private static final int MESSAGE_SENT = 2;
    private final Context context;
    private List<ResponseModel> chats;

    public ChatAdapter(Context context, List<ResponseModel> chats) {
        this.context = context;
        this.chats = chats;
    }

    public void insertListItem(ResponseModel responseModel) {
        chats.add(responseModel);
        notifyItemInserted(chats.size());
        notifyDataSetChanged();
    }

    public void updateListItem(ResponseModel responseModel) {
        chats.set(chats.size() - 1, responseModel);
        notifyItemChanged(chats.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MESSAGE_RECEIVE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_receive, parent, false);
            return new MessageReceiveHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new MessageSentHolder(view);
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResponseModel chatModel = chats.get(position);
        if (holder.getItemViewType() == MESSAGE_RECEIVE) {
            ((MessageReceiveHolder) holder).tvReceiveBody.setText(chatModel.getMessage());
            String date = chats.get(position).getCreatedAt();
            String dateFormat = Utils.setDateFormat(date, "yyyy-MM-dd hh:mm:ss", "hh:mm a");
            ((MessageReceiveHolder) holder).tvReceiveTime.setText(dateFormat);

        } else {
            ((MessageSentHolder) holder).tvSentBody.setText(chatModel.getMessage());
            String date = chats.get(position).getCreatedAt();
            String dateFormat = Utils.setDateFormat(date, "yyyy-MM-dd hh:mm:ss", "hh:mm a");
            ((MessageSentHolder) holder).tvSentTime.setText(dateFormat);
            if (chats.get(position).isNewItemAdded()) {
                Glide.with(context).load(R.drawable.ic_baseline_block_24).into(((MessageSentHolder) holder).iv_check);
            } else {
                Glide.with(context).load(R.drawable.ic_baseline_done_all_24).into(((MessageSentHolder) holder).iv_check);
            }
        }
    }

    @Override
    public int getItemCount() {
        return chats != null ? chats.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return chats.get(position).getUserId() == 1 ? MESSAGE_RECEIVE : MESSAGE_SENT;
    }


    private static class MessageReceiveHolder extends RecyclerView.ViewHolder {
        private final TextView tvReceiveBody;
        private final TextView tvReceiveTime;

        public MessageReceiveHolder(@NonNull View itemView) {
            super(itemView);
            tvReceiveBody = itemView.findViewById(R.id.tv_receive_body);
            tvReceiveTime = itemView.findViewById(R.id.tv_receive_time);
        }
    }

    private static class MessageSentHolder extends RecyclerView.ViewHolder {
        private final TextView tvSentBody;
        private final TextView tvSentTime;
        private final ImageView iv_check;

        public MessageSentHolder(View view) {
            super(view);
            tvSentBody = view.findViewById(R.id.tv_sent_body);
            tvSentTime = view.findViewById(R.id.tv_sent_time);
            iv_check = view.findViewById(R.id.iv_check);
        }
    }
}

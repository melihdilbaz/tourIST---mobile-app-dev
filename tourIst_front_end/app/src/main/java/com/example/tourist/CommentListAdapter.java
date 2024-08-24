package com.example.tourist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>{

    Context ctx;

    List<Comment> data;

    public CommentListAdapter(Context ctx, List<Comment> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(ctx).inflate(R.layout.comment_row, parent, false);
        CommentListAdapter.CommentViewHolder holder = new CommentListAdapter.CommentViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.txtCommentOwner.setText(data.get(position).getName());
        holder.txtCommentBody.setText(data.get(position).getText());

        ExecutorService srv = ((WebApplication) ((Activity)ctx).getApplication()).srv;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout row;
        TextView txtCommentOwner;
        TextView txtCommentBody;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.comment_row_list);
            txtCommentOwner = itemView.findViewById(R.id.txtCommentOwner);
            txtCommentBody = itemView.findViewById(R.id.txtCommentBody);
        }
    }
}

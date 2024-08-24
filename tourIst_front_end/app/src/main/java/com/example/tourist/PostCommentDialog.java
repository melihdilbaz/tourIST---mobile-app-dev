package com.example.tourist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.ExecutorService;

public class PostCommentDialog extends DialogFragment {
    EditText txtName;
    EditText txtComment;
    Button btnPost;
    PostCommentDialogListener listener;
    private String destinationId;

    public void setDestinationId(String id) {
        this.destinationId = id;
    }
    Handler postResponseHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.d("Navigation", "Comment is posted: " + (String)msg.obj);
            return true;
        }
    });
    public PostCommentDialog(PostCommentDialogListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_post_comment,container,false);
        txtName = v.findViewById(R.id.txtName);
        txtComment = v.findViewById(R.id.txtComment);
        btnPost = v.findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.commentEntered(txtName.getText().toString(), txtComment.getText().toString());
                Comment comm = new Comment();
                comm.setName(txtName.getText().toString());
                comm.setText(txtComment.getText().toString());
                CommentRepository commRepo = new CommentRepository();
                ExecutorService srv = ((WebApplication) getActivity().getApplication()).srv;

                commRepo.postComment(srv, postResponseHandler, destinationId, comm);
                Toast.makeText(getActivity(), "To see your new itinerary, please refresh the page!", Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
        return v;

    }
    public interface PostCommentDialogListener{
        public void commentEntered(String name, String comment);
    }
}
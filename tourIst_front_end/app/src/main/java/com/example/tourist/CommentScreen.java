package com.example.tourist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tourist.databinding.FragmentCommentScreenBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class CommentScreen extends Fragment implements PostCommentDialog.PostCommentDialogListener {

    //FragmentCommentScreenBinding binding;
    private FragmentCommentScreenBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<Comment> data = (List<Comment>)msg.obj;

            CommentListAdapter adp = new CommentListAdapter(getActivity(),data);
            binding.recViewComments.setAdapter(adp);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment using View Binding
        binding = FragmentCommentScreenBinding.inflate(inflater, container, false);
        binding.recViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (getArguments() != null) {
            String id = getArguments().getString("id");

            CommentRepository commRepo = new CommentRepository();
            ExecutorService srv = ((WebApplication) getActivity().getApplication()).srv;

            commRepo.getAllCommentsByDestinationId(srv, handler, id);

            // Set up the Post Comment button
            binding.btnPostComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPostCommentDialog(id);
                }
            });
        }

        return binding.getRoot();
    }

    private void showPostCommentDialog(String id) {
        FragmentManager fm = getParentFragmentManager();
        PostCommentDialog dialog = new PostCommentDialog(this);
        dialog.setDestinationId(id);
        dialog.show(fm, "PostCommentDialog");
    }

    @Override
    public void commentEntered(String name, String comment) {
        // Handle the entered comment
        //comm.setText(name);
        //comm.setText(comment);

    }
}
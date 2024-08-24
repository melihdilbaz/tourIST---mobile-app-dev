package com.example.tourist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourist.databinding.FragmentSearchResultScreenBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class SearchResultScreen extends Fragment {
    FragmentSearchResultScreenBinding binding;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Destination> data = (List<Destination>)msg.obj;

            DestinationListAdapter adp = new DestinationListAdapter(getActivity(),data);
            binding.recViewDesList.setAdapter(adp);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchResultScreenBinding.inflate(getLayoutInflater());
        binding.recViewDesList.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (getArguments() != null) {
            Log.d("Navigation", "Received Category Name: " + getArguments().getString("catName"));
            String catName = getArguments().getString("catName");

            DestinationRepository destRepo = new DestinationRepository();
            ExecutorService srv = ((WebApplication) getActivity().getApplication()).srv;

            destRepo.getAllDestinationsByCategoryName(srv, handler, catName);

        }

        return binding.getRoot();
    }
}
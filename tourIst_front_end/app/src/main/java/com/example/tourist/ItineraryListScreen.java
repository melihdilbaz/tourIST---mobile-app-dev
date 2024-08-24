package com.example.tourist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tourist.databinding.FragmentItineraryListScreenBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class ItineraryListScreen extends Fragment implements AddItineraryDialog.AddItineraryDialogListener {
    FragmentItineraryListScreenBinding binding;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Itinerary> data = (List<Itinerary>)msg.obj;

            ItineraryListAdapter adp = new ItineraryListAdapter(getActivity(),data);
            binding.recViewItineraryList.setAdapter(adp);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItineraryListScreenBinding.inflate(inflater, container, false);
        binding.recViewItineraryList.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItineraryRepository itRepo = new ItineraryRepository();
        ExecutorService srv = ((WebApplication) getActivity().getApplication()).srv;

        itRepo.getAllItineraries(srv, handler);

        binding.btnCreateItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItineraryDialog();
            }
        });

        return binding.getRoot();
    }

    private void showAddItineraryDialog() {
        FragmentManager fm = getParentFragmentManager();
        AddItineraryDialog dialog = new AddItineraryDialog(this);
        dialog.show(fm, "AddItineraryDialog");
    }

    @Override
    public void itineraryAdded(String itineraryName) {
        // Handle the added itinerary, e.g., update RecyclerView
        // For demonstration purposes, let's just log the added itinerary name
        Log.d("ItineraryListScreen", "New itinerary added: " + itineraryName);
    }
}
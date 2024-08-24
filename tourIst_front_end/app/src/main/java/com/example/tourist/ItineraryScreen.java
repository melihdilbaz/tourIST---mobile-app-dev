package com.example.tourist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tourist.databinding.FragmentItineraryScreenBinding;
import com.example.tourist.databinding.FragmentMainScreenBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class ItineraryScreen extends Fragment {
    FragmentItineraryScreenBinding binding;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Destination> data = (List<Destination>)msg.obj;

            ItineraryDestinationListAdapter adp = new ItineraryDestinationListAdapter(getActivity(),data);
            binding.recViewItineraryDesList.setAdapter(adp);

            return true;
        }
    });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItineraryScreenBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        binding.recViewItineraryDesList.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (getArguments() != null) {
            String itineraryId = getArguments().getString("itineraryId");
            String itineraryName = getArguments().getString("itineraryName");
            binding.txtItineraryTitle.setText(itineraryName);

            ItineraryRepository itRepo = new ItineraryRepository();
            ExecutorService srv = ((WebApplication) getActivity().getApplication()).srv;

            itRepo.getItineraryById(srv, handler, itineraryId);

            binding.btnAddDes.setOnClickListener(v->{

                itRepo.addItinerary(srv, itineraryName, binding.txtItineraryAdd.getText().toString());
                Toast.makeText(getActivity(), "To see your new itinerary, please refresh the page!", Toast.LENGTH_SHORT).show();
            });
            binding.btnDropDes.setOnClickListener(v->{

                itRepo.dropItinerary(srv, itineraryName);
                Toast.makeText(getActivity(), "To see your new itinerary, please refresh the page!", Toast.LENGTH_SHORT).show();
            });
        }

        return binding.getRoot();
    }
}
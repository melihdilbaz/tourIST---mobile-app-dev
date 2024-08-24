package com.example.tourist;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourist.databinding.FragmentDetailedInfoScreenBinding;
import com.example.tourist.databinding.FragmentMainScreenBinding;

import java.util.concurrent.ExecutorService;


public class DetailedInfoScreen extends Fragment {
    FragmentDetailedInfoScreenBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Destination dest = (Destination) msg.obj;
            binding.txtDestNameDetails.setText(dest.getName());
            binding.txtViewDes.setText(dest.getText());

            ((MainActivity)getActivity()).getToolBar().setTitle(dest.getName());


            DestinationRepository repo = new DestinationRepository();
            ExecutorService srv = ((WebApplication)getActivity().getApplication()).srv;
            repo.downloadImage(srv,imgHandler,dest.getImage());



            return true;

        }
    });

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            binding.imgDes.setImageBitmap((Bitmap)msg.obj);


            return true;
        }
    });
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailedInfoScreenBinding.inflate(getLayoutInflater());

        String id = getArguments().getString("id");

        DestinationRepository destRepo = new DestinationRepository();
        ExecutorService srv = ((WebApplication)getActivity().getApplication()).srv;
        destRepo.getDestination(srv, handler, id);

        binding.btnComments.setOnClickListener(v->{

            NavController navController
                    = Navigation.findNavController(getActivity(),R.id.fragmentContainerView2);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("id", id);
            navController.navigate(R.id.action_detailedInfoScreen_to_commentScreen, dataBundle);

        });


        return binding.getRoot();
    }
}
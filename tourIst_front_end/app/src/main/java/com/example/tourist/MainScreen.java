package com.example.tourist;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.media3.common.util.Log;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.tourist.databinding.FragmentMainScreenBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class MainScreen extends Fragment {
    List<Category> data = new ArrayList<>();
    FragmentMainScreenBinding binding;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            data = (List<Category>) msg.obj;

            ArrayAdapter<Category> adp =
                    new ArrayAdapter<>(getActivity(), android.R.layout.simple_selectable_list_item, data);
            binding.categoryListView.setAdapter(adp);


            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            // Inflate the layout for this fragment
        binding = FragmentMainScreenBinding.inflate(getLayoutInflater());

        binding.btnItineraries.setOnClickListener(v->{
            NavController navController
                    = Navigation.findNavController(getActivity(),R.id.fragmentContainerView2);

            navController.navigate(R.id.action_mainScreen_to_itineraryListScreen);

        });

        binding.categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String catName = data.get(position).toString();

                Bundle dataBundle = new Bundle();
                dataBundle.putString("catName",catName);

                NavController navController
                        = Navigation.findNavController(getActivity(),R.id.fragmentContainerView2);
                navController.navigate(R.id.action_mainScreen_to_searchResultScreen, dataBundle);

            }
        });

        //binding.categoryListView.setLayoutAnimation(new LinearLayout(getActivity()).getLayoutAnimation());
        CategoryRepository catRepo = new CategoryRepository();
        ExecutorService srv = ((WebApplication) getActivity().getApplication()).srv;

        catRepo.getAllCategories(srv, handler);

        return binding.getRoot();
    }
}
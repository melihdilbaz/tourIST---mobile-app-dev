package com.example.tourist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ItineraryListAdapter extends RecyclerView.Adapter<ItineraryListAdapter.ItineraryViewHolder> {

    Context ctx;

    List<Itinerary> data;

    public ItineraryListAdapter(Context ctx, List<Itinerary> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public ItineraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(ctx).inflate(R.layout.row_list_itinerary_item, parent, false);
        ItineraryViewHolder holder = new ItineraryViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItineraryViewHolder holder, int position) {
        holder.txtName.setText(data.get(position).getName());

        String destSize = String.valueOf(data.get(position).getList().size());
        holder.txtTimeUpdate.setText(destSize);


        String id = data.get(position).getId();
        String name = data.get(position).getName();
        holder.row.setOnClickListener(v->{
            Log.d("Navigation", "Received Category Name: " + destSize);
            NavController navController =
                    Navigation.findNavController((Activity) ctx,R.id.fragmentContainerView2);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("itineraryId", id);
            dataBundle.putString("itineraryName", name);
            navController.navigate(R.id.action_itineraryListScreen_to_itineraryScreen, dataBundle);

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItineraryViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout row;
        TextView txtName;
        TextView txtTimeUpdate;
        public ItineraryViewHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.row_itinerary_list);
            txtName = itemView.findViewById(R.id.txtItineraryName);
            txtTimeUpdate = itemView.findViewById(R.id.txtLastUpdatedTimeView);
        }



    }
}

package com.example.tourist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.LogRecord;

public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.DestinationViewHolder> {
    Context ctx;

    List<Destination> data;

    public DestinationListAdapter(Context ctx, List<Destination> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(ctx).inflate(R.layout.row_list_dest_item, parent, false);
        DestinationViewHolder holder = new DestinationViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        holder.txtDestName.setText(data.get(position).getName());

        ExecutorService srv = ((WebApplication) ((Activity)ctx).getApplication()).srv;

        holder.downloadImage(srv,data.get(position).getImage());

        String id = data.get(position).getId();

        holder.row.setOnClickListener(v->{
            NavController navController =
                    Navigation.findNavController((Activity) ctx,R.id.fragmentContainerView2);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("id", id);
            navController.navigate(R.id.action_searchResultScreen_to_detailedInfoScreen, dataBundle);

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DestinationViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout row;
        TextView txtDestName;
        ImageView ImgListDestItem;

        boolean imageDownloaded;
        Handler imageHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                ImgListDestItem.setImageBitmap((Bitmap) msg.obj);
                imageDownloaded = true;

                return true;
            }
        });
        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.row_list);
            txtDestName = itemView.findViewById(R.id.txtDestName);
            ImgListDestItem = itemView.findViewById(R.id.ImgListDestItem);
        }

        public void downloadImage(ExecutorService srv, String path){

            if(!imageDownloaded){
                DestinationRepository destRepo = new DestinationRepository();
                destRepo.downloadImage(srv,imageHandler,path);
            }


        }
    };


}

package com.example.tourist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.ExecutorService;

public class AddItineraryDialog extends DialogFragment {
    EditText txtItinerary;
    Button btnAdd;
    AddItineraryDialogListener listener;

    public AddItineraryDialog(AddItineraryDialogListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_add_itinerary, container, false);
        txtItinerary = v.findViewById(R.id.txtItinerary);
        btnAdd = v.findViewById(R.id.btnCreate);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itineraryAdded(txtItinerary.getText().toString());

                ItineraryRepository itRepo = new ItineraryRepository();
                ExecutorService srv = ((WebApplication) getActivity().getApplication()).srv;

                itRepo.createItinerary(srv, txtItinerary.getText().toString());
                Log.d("Navigation", "itinerary created : " + txtItinerary.getText().toString());

                dismiss();
            }
        });
        return v;
    }

    public interface AddItineraryDialogListener {
        void itineraryAdded(String itineraryName);
    }
}
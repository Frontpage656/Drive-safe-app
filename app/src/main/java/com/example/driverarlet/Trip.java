package com.example.driverarlet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Trip extends AppCompatActivity {

    RecyclerView tripRecycleView;
    RecyclerView.LayoutManager linerLayout;
    TripRecycleAdapter tripAdapter;
    List<TripModelClass> list = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        tripRecycleView = findViewById(R.id.sokoni_products);

        linerLayout = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        tripRecycleView.setLayoutManager(linerLayout);

        tripAdapter = new TripRecycleAdapter(getApplicationContext(),list);

        tripRecycleView.setAdapter(tripAdapter);
        addAll();


    }
    private void addAll() {
        db.collection("trips").orderBy("from", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(getApplicationContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            for (DocumentChange documentChange : value.getDocumentChanges()) {
                                if (documentChange.getType() == DocumentChange.Type.ADDED) {

                                    list.add(documentChange.getDocument().toObject(TripModelClass.class));

                                }

                                tripAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
}
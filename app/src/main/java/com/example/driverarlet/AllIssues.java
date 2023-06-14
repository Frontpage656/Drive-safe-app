package com.example.driverarlet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllIssues extends AppCompatActivity {

    TextView passed;
    FrameLayout google_map_frg;
    TextView issues_count;

    Toolbar toolbar;


    RecyclerView postsRecycleView;
    RecyclerView.LayoutManager linearLayout;
    PostAdapter postAdapter;
    List<PostModelClass> list = new ArrayList<>();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_issues);


        Intent intent = getIntent();
        String data = intent.getStringExtra("name");

       // getWindow().setStatusBarColor(getApplicationContext().getColor(Color.transparent));
        toolbar = findViewById(R.id.tooBar);

        issues_count = findViewById(R.id.sellers_count);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        google_map_frg = findViewById(R.id.google_map_frg);


        google_map_frg = findViewById(R.id.google_map_frg);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.google_map_frg, new MapFragment())
                .commit();


        postsRecycleView = findViewById(R.id.sellers_list);

        postsRecycleView.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(getApplicationContext());
        postsRecycleView.setLayoutManager(linearLayout);

        postAdapter = new PostAdapter(getApplicationContext(), list);
        postsRecycleView.setAdapter(postAdapter);


        getAllValues();



    }

    private void getAllValues() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("name");


        firestore.collection("issues").document(String.valueOf(data)).collection("list")
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

                                    list.add(documentChange.getDocument().toObject(PostModelClass.class));

                                }
                                postAdapter.notifyDataSetChanged();

                                issues_count.setText(postsRecycleView.getAdapter().getItemCount() + " Issues " +"("+data+")");

                            }
                        }
                    }
                });
    }

}
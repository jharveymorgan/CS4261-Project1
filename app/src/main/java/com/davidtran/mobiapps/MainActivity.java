package com.davidtran.mobiapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAddtoList;
    private DatabaseReference mDatabase;
    private final List<String> todoList = new ArrayList<>();
    private ListView mListView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mListView = findViewById(R.id.list_view_to_do);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, android.R.id.text1, todoList);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    arrayAdapter.add(ds.getKey());
                    System.out.println("XXXXXXXXXXXXXXXXX" + ds.getKey());
                }
                mListView.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        mDatabase.addListenerForSingleValueEvent(eventListener);

        btnAddtoList = findViewById(R.id.btn_add_to_list);
        btnAddtoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SubmitFormActivity.class));
                finish();
            }
        });
    }

}

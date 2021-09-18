package com.cihan.gozenringservis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DriverActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        mRecyclerView =(RecyclerView) findViewById(R.id.requestRV);

        new FirebaseDatabaseHelper().readRequests(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Request> requests, List<String> keys) {
                findViewById(R.id.loading_request_pb).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView,DriverActivity.this,requests,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
        



    }
}
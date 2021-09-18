package com.cihan.gozenringservis;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceRequest;
    private Query query;
    private List<Request> requests = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Request> requests, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
       // mReferenceRequest = mDatabase.getReference("requests");
       mReferenceRequest = mDatabase.getReference();
       query = mReferenceRequest.child("requests").orderByChild("time");
    }

    public void readRequests(final DataStatus dataStatus){
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                requests.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Request request = keyNode.getValue(Request.class);
                    requests.add(request);
                }
                dataStatus.DataIsLoaded(requests,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void updateRequest(String key, Request request,final DataStatus dataStatus){
           mReferenceRequest.child("requests").child(key).setValue(request).addOnSuccessListener(new OnSuccessListener<Void>() {
               @Override
               public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
               }
           });
    }

    public void deleteRequest(String key, final DataStatus dataStatus){
        mReferenceRequest.child("requests").child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
               dataStatus.DataIsDeleted();
            }
        });

    }
}

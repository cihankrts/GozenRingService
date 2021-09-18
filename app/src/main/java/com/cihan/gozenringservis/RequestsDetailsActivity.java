package com.cihan.gozenringservis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RequestsDetailsActivity extends AppCompatActivity {

    private TextView txtStation1;
    private TextView txtStation2;
    private TextView txttime;
    private EditText edtnewCount;
    private String alınanKisi;

    private Button btnUpdate;
    private Button btnDelete;

    private String key;
    private String mStation1;
    private String mStation2;
    private String mtime;
    private String mCount;

    public void init(){
        key = getIntent().getStringExtra("key");
        mtime = getIntent().getStringExtra("key");
        mStation1 = getIntent().getStringExtra("station1");
        mStation2 = getIntent().getStringExtra("station2");
        mCount = getIntent().getStringExtra("count");

        txtStation1 = (TextView) findViewById(R.id.txtsts1);
        txtStation1.setText(mStation1);
        txtStation2 = (TextView) findViewById(R.id.txtsts2);
        txtStation2.setText(mStation2);
        txttime = (TextView) findViewById(R.id.txttime);
        txttime.setText(mtime);
        edtnewCount = (EditText) findViewById(R.id.edtNewCount);
        edtnewCount.setText(mCount);
        btnUpdate = (Button) findViewById(R.id.btnUpt);
        btnDelete = (Button) findViewById(R.id.btnDlt);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_details);
        init();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request();
                request.setStation1(txtStation1.getText().toString());
                request.setStation2(txtStation2.getText().toString());
                request.setTime(txttime.getText().toString());
                alınanKisi = edtnewCount.getText().toString();
                request.setCount(String.valueOf(Integer.parseInt(mCount)-Integer.parseInt(alınanKisi)));

                new FirebaseDatabaseHelper().updateRequest(key, request, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Request> requests, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(RequestsDetailsActivity.this,"Updated.."
                                ,Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteRequest(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Request> requests, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(RequestsDetailsActivity.this,"Nobody left..",
                                Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                });
            }
        });

    }
}
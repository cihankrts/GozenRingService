package com.cihan.gozenringservis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

      private Button btnDriver;
      private String myCode;

    private Spinner spinnerStation;
    private Spinner spinnerStation2;
    private EditText count;
    private Button send;


    FirebaseDatabase db;
    DatabaseReference reference;

    public void init(){

        btnDriver = (Button) findViewById(R.id.btndriver);

        count = (EditText) findViewById(R.id.edtCount);
        send = (Button) findViewById(R.id.btnSend);
        spinnerStation = (Spinner) findViewById(R.id.spinnerStation);
        ArrayAdapter<CharSequence> adapterStation = ArrayAdapter.createFromResource(this,
                R.array.stations,R.layout.spinner_stations);
        adapterStation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStation.setAdapter(adapterStation);

        spinnerStation2 = (Spinner) findViewById(R.id.spinnerStation2);
        ArrayAdapter<CharSequence> adapterStation2 = ArrayAdapter.createFromResource(this,
                R.array.stations,R.layout.spinner_stations);
        adapterStation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStation2.setAdapter(adapterStation);

        db = FirebaseDatabase.getInstance();
        reference = db.getReference();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               kayitOlustur();
               count.setText("");
            }
        });

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this,R.style.AlertDialogTheme);
                mydialog.setTitle("Sürücü girişi için şifreyi giriniz.");

                final EditText code = new EditText(MainActivity.this);
                code.setHintTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                code.setHint("şifre");
                code.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                mydialog.setView(code);

                mydialog.setPositiveButton("Giriş yap", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myCode = code.getText().toString();
                        if (myCode.equals("Qwerty"))
                        {
                            Intent intent = new Intent(MainActivity.this, DriverActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this,"Soför girişi gerçekleşti.",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }  else {
                            Toast.makeText(MainActivity.this,"Giriş başarısız oldu.",
                                    Toast.LENGTH_LONG).show();
                            code.setText("");
                        }
                    }
                }) ;

                mydialog.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                    }
                });
                mydialog.show();
                
            }
        });





    }

    private void kayitOlustur() {

        String sdf = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss", Locale.getDefault()).format(new Date() );
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());


        String station1 = spinnerStation.getSelectedItem().toString();
        String station2 = spinnerStation2.getSelectedItem().toString();
        String person = count.getText().toString();

        DestinationHelperClass destinationHelperClass = new DestinationHelperClass(station1,
                station2,person,currentTime);
        reference.child("requests").child(currentTime).setValue(destinationHelperClass);
     
        Toast.makeText(this,"Güzergah iletildi..",Toast.LENGTH_LONG).show();
    }


}
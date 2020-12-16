package com.example.pemesanantiket.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pemesanantiket.R;
import com.example.pemesanantiket.adapter.AlertDialogManager;
import com.example.pemesanantiket.session.SessionManager;

public class MainActivity extends AppCompatActivity {

    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        btnLogout = findViewById(R.id.out);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();
            }
        });
    }

        public void profileMenu (View v){
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(i);
        }

        public void historyMenu (View v){
            Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(i);
        }

        public void bookKereta (View v){
            Intent i = new Intent(getApplicationContext(), BookKeretaActivity.class);
            startActivity(i);
        }

        public void bookKeretaLokal (View v){
            Intent i = new Intent(getApplicationContext(), BookKeretaLokalActivity.class);
            startActivity(i);
        }
    }


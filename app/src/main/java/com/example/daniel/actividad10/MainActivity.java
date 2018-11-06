package com.example.daniel.actividad10;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    servicio mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, servicio.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


    public void pararIntentService(View v){
        if(mBound){
            unbindService((mConnection));
            mBound = false;
            Toast.makeText(this, "Servicio desconectado", Toast.LENGTH_SHORT).show();
        }
    }

    public void iniciarIntentService(View v){
        if(!mBound){
            Intent intent = new Intent(this, servicio.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "Servicio iniciado", Toast.LENGTH_SHORT).show();
        }
    }

    public void iniciarService(View v){
        if(mBound){
            pararIntentService(v);
        } else {
            iniciarIntentService(v);
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {

            servicio.cliente binder = (servicio.cliente) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
package com.example.myapplicationirir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("http://www.google.com");

    }
    public void comprobarConexion() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Network red = cm.getActiveNetwork();
        boolean isConnected = red != null;
        if (isConnected) {
            Toast.makeText(this, "Hay conexión a internet", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "No hay conexión a internet", Toast.LENGTH_LONG).show();
        }

    }

    public void comprobarSensor() {
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        boolean sensorExist = mAccelerometer != null;
        if (sensorExist) {
            Toast.makeText(this, "Se ha encontrado un acelerómetro", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "No se ha encontrado ningún sensor del tipo acelerómetro", Toast.LENGTH_LONG).show();
        }
    }

    public void guardarTextoEnSharedPreferences() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        EditText txtCentral = findViewById(R.id.txtCentral);
        editor.putString("textoGuardado", txtCentral.getText().toString());
        editor.apply();
    }

    public void cargarTextoDeSharedPreferences() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String texto = sharedPref.getString("textoGuardado", "");


    }



    public void onClick(View view) {
        if (R.id.botonConexion == view.getId()) {

            comprobarConexion();
        }
        else if (R.id.botonSensor == view.getId()) {

            comprobarSensor();
        }
        else if (R.id.botonGuardar == view.getId()) {

            guardarTextoEnSharedPreferences();
        }
        else if (R.id.botonCargar == view.getId()) {

            cargarTextoDeSharedPreferences();
        }
    }
}
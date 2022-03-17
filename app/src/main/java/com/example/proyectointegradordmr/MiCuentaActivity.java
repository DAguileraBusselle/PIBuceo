package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MiCuentaActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvNombre;
    TextView tvCorreo;
    Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);

        tvNombre = findViewById(R.id.tvNombreCuenta);
        tvCorreo = findViewById(R.id.tvCorreoCuenta);

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(this);


        tvNombre.setText(((BuceoApplication) getApplicationContext()).getUser().getNombreUsuario());
        tvCorreo.setText(((BuceoApplication) getApplicationContext()).getUser().getCorreo());
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
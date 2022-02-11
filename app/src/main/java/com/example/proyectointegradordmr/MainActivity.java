package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectointegradordmr.fragments.LogoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnIniciarSesion;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLogoMain, new LogoFragment()).addToBackStack(null).commit();

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnIniciarSesion.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnIniciarSesion)){
            Intent i = new Intent(this, IniciarSesionActivity.class);
            startActivity(i);
        }
    }
}
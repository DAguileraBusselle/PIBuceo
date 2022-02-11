package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectointegradordmr.fragments.LogoFragment;

public class IniciarSesionActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAceptar;
    Button btnCancelar;

    EditText etUsuario;
    EditText etContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLogoIniciar, new LogoFragment()).addToBackStack(null).commit();

        etUsuario = findViewById(R.id.etUsuario);
        etContrasenia = findViewById(R.id.etContrasenia);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnCancelar)){
            finish();
        }else{
            if (etUsuario.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "No ha introducido el usuario", Toast.LENGTH_SHORT).show();
            }else {
                if (etContrasenia.getText().toString().trim().isEmpty()){
                    Toast.makeText(this, "No ha introducido la contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Sesion iniciada (TEMPORAL)", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectointegradordmr.fragments.LogoFragment;
import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Usuario;

public class IniciarSesionActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAceptar;
    Button btnCancelar;

    EditText etUsuario;
    EditText etContrasenia;

    Usuario user;
    UsuarioDAO UserDAO;
    BuceoDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLogoIniciar, new LogoFragment()).addToBackStack(null).commit();

        db = BuceoDB.getDatabase(this);
        UserDAO = db.UsuarioDAO();

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
            String nombreCorreo = etUsuario.getText().toString().trim();
            String contra = etContrasenia.getText().toString().trim();

            if (nombreCorreo.isEmpty()){
                Toast.makeText(this, "No ha introducido el usuario", Toast.LENGTH_SHORT).show();
            }else {
                if (contra.isEmpty()){
                    Toast.makeText(this, "No ha introducido la contraseña", Toast.LENGTH_SHORT).show();
                }else{
                   if(UserDAO.comprobarNombre(nombreCorreo) == null && UserDAO.comprobarCorreo(nombreCorreo) == null) {
                       Toast.makeText(this, R.string.toast_no_existe, Toast.LENGTH_SHORT).show();
                       ///
                       btnAceptar.setBackgroundColor(getResources().getColor(R.color.rosa));
                   } else if (UserDAO.comprobarSignInNombre(nombreCorreo).equals(contra) || UserDAO.comprobarSignInCorreo(nombreCorreo).equals(contra)) {
                        Intent i = new Intent(this, uwu.class);
                        startActivity(i);

                       //TODO: guardar usuario en myApplication
                   }
                }
            }
        }
    }
}
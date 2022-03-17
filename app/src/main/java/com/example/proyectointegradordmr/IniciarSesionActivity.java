package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Usuario;

public class IniciarSesionActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAceptar;
    Button btnCancelar;

    EditText etUsuario;
    EditText etContrasenia;
    ImageView btnVerContra;

    Usuario user;
    UsuarioDAO UserDAO;
    BuceoDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        db = BuceoDB.getDatabase(this);
        UserDAO = db.UsuarioDAO();

        etUsuario = findViewById(R.id.etUsuario);
        etContrasenia = findViewById(R.id.etContrasenia);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVerContra = findViewById(R.id.btnVerContraInic);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);


        btnVerContra.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int start = etContrasenia.getSelectionStart();
                int end = etContrasenia.getSelectionEnd();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        etContrasenia.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                    case MotionEvent.ACTION_UP:
                        etContrasenia.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;
                }

                etContrasenia.setSelection(start, end);

                return true;
            }
        });

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
                   if(UserDAO.comprobarNombre(nombreCorreo) != null || UserDAO.comprobarCorreo(nombreCorreo) != null) {
                       if(UserDAO.comprobarSignInNombre(nombreCorreo) != null) {
                           if(UserDAO.comprobarSignInNombre(nombreCorreo).equals(contra)) {
                               user = UserDAO.comprobarNombre(nombreCorreo);
                               ((BuceoApplication) getApplicationContext()).setUser(user);
                               Intent i = new Intent(this, MapaActivity.class);

                               startActivity(i);
                           } else {
                               Toast.makeText(this, R.string.toast_contra_incorrecta, Toast.LENGTH_SHORT).show();
                           }

                       } else if (UserDAO.comprobarSignInCorreo(nombreCorreo) != null) {
                           if (UserDAO.comprobarSignInCorreo(nombreCorreo).equals(contra)) {
                               user = UserDAO.comprobarCorreo(nombreCorreo);
                               ((BuceoApplication) getApplicationContext()).setUser(user);
                               Intent i = new Intent(this, MapaActivity.class);

                               startActivity(i);
                           } else {
                               Toast.makeText(this, R.string.toast_contra_incorrecta, Toast.LENGTH_SHORT).show();
                           }
                       }
                   } else {
                       Toast.makeText(this, R.string.toast_no_existe, Toast.LENGTH_SHORT).show();
                   }
                }
            }
        }
    }
}
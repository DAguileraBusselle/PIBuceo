package com.example.proyectointegradordmr;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectointegradordmr.fragments.LogoFragment;
import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Usuario;
import com.google.android.material.badge.BadgeUtils;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNombre;
    EditText etCorreo;
    EditText etContra;
    EditText etContraCheck;

    Button btnRegistrar;
    Button btnCancelar;

    ImageView btnVerContra;

    Usuario user;
    UsuarioDAO userDAO;
    BuceoDB db;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        etNombre = findViewById(R.id.etUsuarioRegistar);
        etCorreo = findViewById(R.id.etCorreo);
        etContra = findViewById(R.id.etContraseniaRegistrar);
        etContraCheck = findViewById(R.id.etContraseniaCheck);


        btnCancelar = findViewById(R.id.btnCancelarRegistar);
        btnRegistrar = findViewById(R.id.btnRegistrarRegistrar);

        btnRegistrar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        btnVerContra = findViewById(R.id.btnVerContra);

        db = BuceoDB.getDatabase(this);
        userDAO = db.UsuarioDAO();


        btnRegistrar.setEnabled(false);

        btnVerContra.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        etContra.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                    case MotionEvent.ACTION_UP:
                        etContra.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;
                }

                return true;
            }
        });

        etContraCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etContraCheck.getText().toString().equals(etContra.getText().toString())) {
                    btnRegistrar.setEnabled(true);
                    etContraCheck.setBackgroundColor(Color.TRANSPARENT);

                } else {
                    btnRegistrar.setEnabled(false);
                    etContraCheck.setBackgroundColor(getResources().getColor(R.color.rojo_suave));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etContra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etContraCheck.getText().toString().equals(etContra.getText().toString())) {
                    btnRegistrar.setEnabled(true);
                    etContraCheck.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    btnRegistrar.setEnabled(false);
                    etContraCheck.setBackgroundColor(getResources().getColor(R.color.rojo_suave));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnCancelar)) {
            i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String contra = etContra.getText().toString().trim();
            String contraCheck = etContraCheck.getText().toString().trim();

            if(nombre.isEmpty() || correo.isEmpty() ||
                    contra.isEmpty() || contraCheck.isEmpty()) {
                Toast.makeText(this, R.string.toast_vacio, Toast.LENGTH_SHORT).show();

            } else {
                if (!contraCheck.equals(contra)) {
                    Toast.makeText(this, R.string.toast_contra_check_fail, Toast.LENGTH_SHORT).show();

                } else if (userDAO.comprobarNombre(nombre) != null) {
                    Toast.makeText(this, R.string.toast_nombre_existe, Toast.LENGTH_SHORT).show();


                } else if (userDAO.comprobarCorreo(correo) != null) {
                    Toast.makeText(this, R.string.toast_correo_existe, Toast.LENGTH_SHORT).show();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    Toast.makeText(this, R.string.toast_correo_invalido, Toast.LENGTH_SHORT).show();

                } else if (Patterns.EMAIL_ADDRESS.matcher(nombre).matches()) {
                    Toast.makeText(this, R.string.nombre_no_correo, Toast.LENGTH_SHORT).show();

                } else {
                    user = new Usuario(nombre, correo, contra);
                    userDAO.insertarUsuario(user);
                    Toast.makeText(this, R.string.user_registrado, Toast.LENGTH_SHORT).show();

                    finish();
                    i = new Intent(this, IniciarSesionActivity.class);
                    startActivity(i);

                }
            }
        }
    }

}
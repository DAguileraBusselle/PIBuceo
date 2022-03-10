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
import com.google.android.material.badge.BadgeUtils;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre;
    EditText etCorreo;
    EditText etContra;
    EditText etContraCheck;

    Button btnRegistrar;
    Button btnCancelar;

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

        db = BuceoDB.getDatabase(this);
        userDAO = db.UsuarioDAO();

    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnCancelar)) {
            finish();
        } else {
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String contra = etContra.getText().toString().trim();
            String contraCheck = etContraCheck.getText().toString().trim();

            if(nombre.isEmpty() || correo.isEmpty() ||
                    contra.isEmpty() || contraCheck.isEmpty()) {
                Toast.makeText(this, R.string.toast_vacio, Toast.LENGTH_SHORT).show();

                ///
                btnRegistrar.setBackgroundColor(getResources().getColor(R.color.blue));

                ///

            } else {
                if (!contraCheck.equals(contra)) {
                    Toast.makeText(this, R.string.toast_contra_check_fail, Toast.LENGTH_SHORT).show();

                    ///
                    btnRegistrar.setBackgroundColor(getResources().getColor(R.color.verde));

                    ///
                } else if (userDAO.comprobarNombre(nombre) != null) {
                    Toast.makeText(this, R.string.toast_nombre_existe, Toast.LENGTH_SHORT).show();

                    ///
                    btnRegistrar.setBackgroundColor(getResources().getColor(R.color.red));

                    ///

                } else if (userDAO.comprobarCorreo(correo) != null) {
                    Toast.makeText(this, R.string.toast_correo_existe, Toast.LENGTH_SHORT).show();

                    ///
                    btnRegistrar.setBackgroundColor(getResources().getColor(R.color.amarillo));

                    ///
                } else {
                    user = new Usuario(nombre, correo, contra);
                    userDAO.insertarUsuario(user);
                    Toast.makeText(this, R.string.user_registrado, Toast.LENGTH_SHORT).show();

                    ///
                    btnRegistrar.setBackgroundColor(getResources().getColor(R.color.rosa));

                    ///
                    i = new Intent(this, IniciarSesionActivity.class);
                    startActivity(i);

                }
            }
        }
    }
}
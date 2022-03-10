package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Usuario;

import java.util.ArrayList;

public class uwu extends AppCompatActivity {

    TextView tv;

    ArrayList<Usuario> listaUsers;

    BuceoDB db;
    UsuarioDAO dao;

    String texto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uwu);

        tv = findViewById(R.id.tv);
        tv.setMovementMethod(new ScrollingMovementMethod());

        db = BuceoDB.getDatabase(this);
        dao = db.UsuarioDAO();

        listaUsers = (ArrayList<Usuario>) dao.check();

        for (Usuario user : listaUsers) {
            texto += user.getNombreUsuario() + " | " + user.getCorreo() + " | " + user.getPassword() + "\n";
        }
        tv.setText(texto);
    }
}
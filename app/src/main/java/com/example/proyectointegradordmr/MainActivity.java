package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectointegradordmr.fragments.LogoFragment;
import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Centro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnIniciarSesion;
    Button btnRegistrar;
    Button btn;

    ArrayList<Centro> listaCentrosInsert;
    CentroDAO centroDao;
    BuceoDB db;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLogoMain, new LogoFragment()).addToBackStack(null).commit();
        
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btn = findViewById(R.id.btn);

        db = BuceoDB.getDatabase(this);
        centroDao = db.CentroDAO();

        listaCentrosInsert = (ArrayList<Centro>) centroDao.selectAll();
        if (listaCentrosInsert.size() == 0) {
            listaCentrosInsert.add(new Centro("Ocean Addicts", 36.010794, -5.604684));
            listaCentrosInsert.add(new Centro("Buceo La Graciosa", 29.230309, -13.502661));
            listaCentrosInsert.add(new Centro("Dive College", 28.862314, -13.854228));
            listaCentrosInsert.add(new Centro("Revolution Dive", 38.535965, -0.132423));

            for (Centro centro : listaCentrosInsert) {
                centroDao.insertarCentros(centro);
            }
        }

        btn.setOnClickListener(this);
        btnIniciarSesion.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnIniciarSesion)){
            i = new Intent(this, IniciarSesionActivity.class);
            startActivity(i);
        } else if (v.equals(btn)) {
            i = new Intent(this, uwu.class);
            startActivity(i);
        } else {
            i = new Intent(this, RegistrarActivity.class);
            startActivity(i);
        }
    }
}
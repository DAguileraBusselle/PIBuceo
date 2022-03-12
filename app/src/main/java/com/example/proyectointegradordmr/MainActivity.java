package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectointegradordmr.fragments.LogoFragment;
import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DAO.InmersionDAO;
import com.example.proyectointegradordmr.room.DAO.ReseniaDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Centro;
import com.example.proyectointegradordmr.room.Entity.Inmersion;
import com.example.proyectointegradordmr.room.Entity.Resenia;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnIniciarSesion;
    Button btnRegistrar;
    Button btn;

    ArrayList<Centro> listaCentrosInsert;
    ArrayList<Inmersion> listaInmersionesInsert;
    ArrayList<Resenia> listaReseniaInsert;
    CentroDAO centroDao;
    BuceoDB db;
    InmersionDAO inmerDao;
    ReseniaDAO reseniaDao;

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
        inmerDao = db.InmersionDAO();
        reseniaDao = db.ReseniaDAO();

        listaCentrosInsert = (ArrayList<Centro>) centroDao.selectAll();
        if (listaCentrosInsert.size() == 0) {
            listaCentrosInsert.add(new Centro("Ocean Addicts", 36.010794, -5.604684, "ocean_addicts_centro"));
            listaCentrosInsert.add(new Centro("Buceo La Graciosa", 29.230309, -13.502661, "la_graciosa_centro"));
            listaCentrosInsert.add(new Centro("Dive College", 28.862314, -13.854228, "dive_college_centro"));
            listaCentrosInsert.add(new Centro("Revolution Dive", 38.535965, -0.132423, "revolution_dive_centro"));

            for (Centro centro : listaCentrosInsert) {
                centroDao.insertarCentros(centro);
            }
        }

        listaInmersionesInsert = (ArrayList<Inmersion>) inmerDao.selectAll();
        inmerDao.deleteInmersion(listaInmersionesInsert);
        listaReseniaInsert = (ArrayList<Resenia>) reseniaDao.selectAll();
        reseniaDao.deleteResenia(listaReseniaInsert);


        reseniaDao.insertarResenia(new Resenia(2, 1, 4.3, "Muy buen centro, pero es muy dificil bucear, la gente es maja, y el equipamiento esta bien cuidado"));
        reseniaDao.insertarResenia(new Resenia(3, 2, 4.5, "Me dieron un sandwich :>"));


        listaInmersionesInsert.clear();
        listaInmersionesInsert.add(new Inmersion(2, "Viaje al pecio perdido", "Una inmersion de barco hasta un pecio en el fondo, con vistas preciosas de un arrecife", "28m", "24.5m",
                "35min", "media", "Advanced Open Water", "18grados", "10grados", "barracudas, dorados, chuchos, sardinas"));
        listaInmersionesInsert.add(new Inmersion(3, "El museo submarino", "Visita una de las vistas mas flipantes del planeta, un terreno submarino lleno de estatuas y esculturas", "20m", "15m",
                "40min", "baja", "Open Water", "20grados", "15grados", "barracudas, sardinas"));
        listaInmersionesInsert.add(new Inmersion(1, "Pierdete en el mar", "jodete", "800m", "740.546m",
                "2 años", "tu que crees", "da igual no hace falta ni botella", "0grados", "0grados", "lo que alucines"));
        inmerDao.insertarInmersiones(listaInmersionesInsert);


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
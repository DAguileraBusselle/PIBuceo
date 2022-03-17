package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DAO.InmersionDAO;
import com.example.proyectointegradordmr.room.DAO.ReseniaDAO;
import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Centro;
import com.example.proyectointegradordmr.room.Entity.Inmersion;
import com.example.proyectointegradordmr.room.Entity.Resenia;
import com.example.proyectointegradordmr.room.Entity.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnIniciarSesion;
    Button btnRegistrar;
    Button btn;

    ArrayList<Centro> listaCentrosInsert;
    ArrayList<Inmersion> listaInmersionesInsert;
    ArrayList<Resenia> listaReseniaInsert;
    ArrayList<Usuario> listaUsuarios;
    CentroDAO centroDao;
    BuceoDB db;
    InmersionDAO inmerDao;
    ReseniaDAO reseniaDao;
    UsuarioDAO userDao;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btn = findViewById(R.id.btn);

        db = BuceoDB.getDatabase(this);
        centroDao = db.CentroDAO();
        inmerDao = db.InmersionDAO();
        reseniaDao = db.ReseniaDAO();
        userDao = db.UsuarioDAO();

        listaUsuarios = (ArrayList<Usuario>) userDao.check();

        if(listaUsuarios.size() == 0) {
            userDao.insertarUsuario(new Usuario("deepExplorer96", "texto@de.ejemplo", "1"));
            userDao.insertarUsuario(new Usuario("Ground_Cero", "segundo@texto.ejemplo", "2"));
            userDao.insertarUsuario(new Usuario("Rivas12", "tercer@texto.ejemplo", "3"));
            userDao.insertarUsuario(new Usuario("BLANDIC", "cuarto@texto.ejemplo", "4"));
            userDao.insertarUsuario(new Usuario("OceanOAzUl3", "quinto@texto.ejemplo", "5"));
            userDao.insertarUsuario(new Usuario("1", "sexto@texto.ejemplo", "1"));
            userDao.insertarUsuario(new Usuario("SebSez","sebastiansaez@yahoo.es","s43Zz"));
            userDao.insertarUsuario(new Usuario("GalvanDiver","ericagalvan@hotmail.com","3RiKa"));
            userDao.insertarUsuario(new Usuario("sSergiSs","sergiseg2020@gmail.com","seg0920"));
            userDao.insertarUsuario(new Usuario("laConchii","conchiprado1987@gmail.com","laChulaEstaxXx"));
            userDao.insertarUsuario(new Usuario("FishingRod","rodolfomascarpone@yahoo.es","20172016"));
            userDao.insertarUsuario(new Usuario("SurferLai","lailawong@gmail.com","L41L4"));
            userDao.insertarUsuario(new Usuario("PauDuran","pauloduran34@hotmail.com","DuranDuran+34"));
            userDao.insertarUsuario(new Usuario("BeWater","belenlucena6@gmail.com","B-L33"));

        }

        listaCentrosInsert = (ArrayList<Centro>) centroDao.selectAll();
        if (listaCentrosInsert.size() == 0) {
            listaCentrosInsert.add(new Centro("Ocean Addicts", 36.010794, -5.604684, "ocean_addicts_centro"));
            listaCentrosInsert.add(new Centro("Buceo La Graciosa", 29.230309, -13.502661, "la_graciosa_centro"));
            listaCentrosInsert.add(new Centro("Dive College", 28.862314, -13.854228, "dive_college_centro"));
            listaCentrosInsert.add(new Centro("Revolution Dive", 38.535965, -0.132423, "revolution_dive_centro"));
            listaCentrosInsert.add(new Centro("Buceo Galicia", 43.368775, -8.388078, "buceo_galicia_centro"));
            listaCentrosInsert.add(new Centro("Nautilus Sub", 38.706755, -9.142790, "nautilus_sub_centro"));


            for (Centro centro : listaCentrosInsert) {
                centroDao.insertarCentros(centro);
            }
        }

        listaReseniaInsert = (ArrayList<Resenia>) reseniaDao.selectAll();

        if (listaReseniaInsert.size() == 0){
            reseniaDao.insertarResenia(new Resenia(1, 1, 4.3, "Muy buen centro, pero es muy dificil bucear, la gente es maja, y el equipamiento esta bien cuidado"));
            reseniaDao.insertarResenia(new Resenia(1, 4, 4.0, "Me dieron un sandwich :>"));
            reseniaDao.insertarResenia(new Resenia(1, 9, 2.4, "El mar es bonito por esta zona pero los buzos de este centro son bordes y no les importa el equipamiento"));
            reseniaDao.insertarResenia(new Resenia(2, 5, 4.5, ""));
            reseniaDao.insertarResenia(new Resenia(2, 2, 5, "mi centor favorito aqwui aprendi a bucear"));
            reseniaDao.insertarResenia(new Resenia(2, 3, 3.7, "hay cosas que se pueden mejorar, el centro podria estar mas limpio, pero en general bastante bien"));
            reseniaDao.insertarResenia(new Resenia(3, 10, 4.1, ""));
            reseniaDao.insertarResenia(new Resenia(3, 7, 3.5, ""));
            reseniaDao.insertarResenia(new Resenia(3, 2, 3.3, "mucha sed"));
            reseniaDao.insertarResenia(new Resenia(4, 8, 4.8, "casi perfecto"));
            reseniaDao.insertarResenia(new Resenia(4, 2, 4.9, ""));
            reseniaDao.insertarResenia(new Resenia(4, 5, 4.5, "equipamiento en buenas condiciones, staff responsable y respetuoso, buen ambiente"));
            reseniaDao.insertarResenia(new Resenia(5, 2, 4.1, ""));
            reseniaDao.insertarResenia(new Resenia(5, 4, 3.5, ""));
            reseniaDao.insertarResenia(new Resenia(5, 8, 3.3, "mar precioso :D"));
            reseniaDao.insertarResenia(new Resenia(6, 3, 4.1, "el equipamiento escaseaba un poco pero a parte de eso todo bien"));
            reseniaDao.insertarResenia(new Resenia(6, 9, 2.3, ""));
            reseniaDao.insertarResenia(new Resenia(6, 1, 0.0, "CASI MUERO"));

            reseniaDao.insertarResenia(new Resenia(1, 2, 3.9, ""));
            reseniaDao.insertarResenia(new Resenia(1, 7, 1.2, ""));
            reseniaDao.insertarResenia(new Resenia(1, 8, 4.4, ""));
            reseniaDao.insertarResenia(new Resenia(2, 4, 3.8, ""));
            reseniaDao.insertarResenia(new Resenia(2, 13, 3, ""));
            reseniaDao.insertarResenia(new Resenia(2, 9, 2.9, ""));
            reseniaDao.insertarResenia(new Resenia(3, 3, 4.1, ""));
            reseniaDao.insertarResenia(new Resenia(3, 5, 4.5, ""));
            reseniaDao.insertarResenia(new Resenia(3, 1, 4.3, ""));
            reseniaDao.insertarResenia(new Resenia(4, 12, 2.5, ""));
            reseniaDao.insertarResenia(new Resenia(4, 3, 4.3, ""));
            reseniaDao.insertarResenia(new Resenia(4, 11, 5, "bueno"));
            reseniaDao.insertarResenia(new Resenia(5, 5, 2.1, ""));
            reseniaDao.insertarResenia(new Resenia(5, 10, 4.5, ""));
            reseniaDao.insertarResenia(new Resenia(5, 3, 4.3, ""));
            reseniaDao.insertarResenia(new Resenia(6, 7, 1.1, ""));
            reseniaDao.insertarResenia(new Resenia(6, 2, 3.6, ""));
            reseniaDao.insertarResenia(new Resenia(6, 14, 1.0, ""));

        }

        listaInmersionesInsert = (ArrayList<Inmersion>) inmerDao.selectAll();

        if (listaInmersionesInsert.size() == 0) {
            listaInmersionesInsert.clear();

            listaInmersionesInsert.add(new Inmersion(1, "Las Piscinas-San Andres", "pecio", "20m", "15.6m",
                    "42min", "baja", "Open Water", "14° Celsius", "8° Celsius", "pulpos, cigalas, medusas, delfines"));
            listaInmersionesInsert.add(new Inmersion(1, "Las Calderas", "pecio, vision reducida", "19.5m", "13.4m",
                    "40min", "media", "Open Water", "17° Celsius", "13° Celsius", "cigalas, tortugas, pulpos"));
            listaInmersionesInsert.add(new Inmersion(1, "Isla Paloma", "arrecife, orilla", "13m", "5.6m",
                    "35min", "muy baja", "ninguno (bautismo)", "17° Celsius", "13° Celsius", "peces locales, pulpos, crustaceos pequeños"));


            listaInmersionesInsert.add(new Inmersion(2, "Cueva del Legionario", "cueva, arrecife", "37m", "24.5m",
                    "28", "media - alta", "Advanced Open Water", "19° Celsius", "13° Celsius", "barracudas, peces locales"));
            listaInmersionesInsert.add(new Inmersion(2, "Roncadoras", "arrecife", "21", "16.3m",
                    "37min", "media", "Open Water", "21° Celsius", "15° Celsius", "angelotes, sardinas"));
            listaInmersionesInsert.add(new Inmersion(2, "Las Anclas", "pecio, corriente", "19.3m", "14.6m",
                    "35", "baja - media", "Open Water", "21° Celsius", "16° Celsius", "angelotes, barracudas, chuchos, peces locales"));


            listaInmersionesInsert.add(new Inmersion(3, "Moray Reef", "Costa, arrecife", "9m", "4.8m",
                    "38min", "muy baja", "ninguno (bautismo)", "22° Celsius", "16° Celsius", "barracudas, dorados, chuchos, sardinas"));
            listaInmersionesInsert.add(new Inmersion(3, "Museo Atlántico", "Esculturas", "14.7m", "11.3m",
                    "40min", "baja", "Open Water", "21° Celsius", "14° Celsius", "bancos de sardinas, barracudas"));
            listaInmersionesInsert.add(new Inmersion(3, "Flamingo Wall", "arrecife", "18m", "13.5m",
                    "27min", "baja", "Open Water", "23° Celsius", "16° Celsius", "bancos de sardinas, crustaceos pequeños, chuchos, barracudas"));


            listaInmersionesInsert.add(new Inmersion(4, "Cara Norte", "arrecife", "22m", "12.7m",
                    "45min", "media", "Open Water", "19° Celsius", "14° Celsius", "rayas, chuchos"));
            listaInmersionesInsert.add(new Inmersion(4, "Isla Benidorm", "arrecife, costa", "21.5", "14.2m",
                    "39min", "baja - media", "Open Water", "22° Celsius", "16° Celsius", "barracudas, pulpos, chuchos"));
            listaInmersionesInsert.add(new Inmersion(4, "Cola Mal Pas", "orilla", "7.8m", "5.5m",
                    "40min", "muy baja", "ninguno (bautismo)", "24° Celsius", "18° Celsius", "peces locales, medusas"));


            listaInmersionesInsert.add(new Inmersion(5, "Costa Fria", "arrecife, costa", "14m", "11.7m",
                    "45min", "baja", "Open Water", "13° Celsius", "10° Celsius", "rayas, chuchos"));
            listaInmersionesInsert.add(new Inmersion(5, "Hiela Huevos", "arrecife", "23.5", "15.6m",
                    "36min", "baja - media", "Open Water", "12° Celsius", "8° Celsius", "pulpos, rayas"));


            inmerDao.insertarInmersiones(listaInmersionesInsert);
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
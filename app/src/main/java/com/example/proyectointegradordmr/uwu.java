package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

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

public class uwu extends AppCompatActivity {

    TextView tv;

    ArrayList<Usuario> listaUsers;
    ArrayList<Centro> listaCentros;
    ArrayList<Inmersion> listaInmers;
    ArrayList<Resenia> listaResenias;

    BuceoDB db;
    UsuarioDAO userDao;
    CentroDAO centroDao;
    ReseniaDAO resenDao;
    InmersionDAO inmerDao;

    String texto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uwu);

        tv = findViewById(R.id.tv);
        tv.setMovementMethod(new ScrollingMovementMethod());

        db = BuceoDB.getDatabase(this);
        userDao = db.UsuarioDAO();
        centroDao = db.CentroDAO();
        resenDao = db.ReseniaDAO();
        inmerDao = db.InmersionDAO();

        listaUsers = (ArrayList<Usuario>) userDao.check();
        listaCentros = (ArrayList<Centro>) centroDao.selectAll();
        listaInmers = (ArrayList<Inmersion>) inmerDao.selectAll();
        listaResenias = (ArrayList<Resenia>) resenDao.selectAll();

        for (Usuario user : listaUsers) {
            texto += user.getNombreUsuario() + " | " + user.getCorreo() + " | " + user.getPassword() + "\n";
        }

        texto += "\n************************************\n";

        for (Centro cen : listaCentros) {
            texto += cen.getNombreCentro() + " | " + cen.getLatitud() + " | " + cen.getLongitud() + "\n";
        }


        texto += "\n************************************\n";

        for (Inmersion inmer : listaInmers) {
            texto += centroDao.selectCentroById(inmer.idCentro).getNombreCentro() + "-->" + inmer.getIdCentro() + " | " + inmer.getNombre() + " | " + inmer.getTituloRecom() + "\n";
        }

        texto += "\n************************************\n";

        for (Resenia resenia : listaResenias) {
            Centro centro = centroDao.selectCentroById(resenia.idCentro);
            Usuario user = userDao.selectUserById(resenia.idUser);
            texto += centro.getNombreCentro() + "-->" + resenia.getIdCentro() + " | " + user.getNombreUsuario() + "-->" + resenia.getIdUser() +" | " + resenia.getCalif() + " | " + resenia.getDesc() + "\n";
        }

        tv.setText(texto);
    }
}
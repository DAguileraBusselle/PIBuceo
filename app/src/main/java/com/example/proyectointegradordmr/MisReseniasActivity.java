package com.example.proyectointegradordmr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.proyectointegradordmr.dialog.MiReseniaDialog;
import com.example.proyectointegradordmr.dialog.OnEliminarReseniaListener;
import com.example.proyectointegradordmr.dialog.OnModificarReseniaListener;
import com.example.proyectointegradordmr.room.DAO.ReseniaDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Resenia;
import com.example.proyectointegradordmr.rvUtils.ResUserAdapter;

import java.util.ArrayList;

public class MisReseniasActivity extends AppCompatActivity implements OnModificarReseniaListener, OnEliminarReseniaListener {

    public static final String CLAVE_RESENIA = "RESENIA";

    ArrayList<Resenia> listaResenias;
    LinearLayoutManager llm;
    ResUserAdapter adapter;

    BuceoDB db;
    ReseniaDAO resDao;
    Resenia res;

    RecyclerView rvResUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_resenias);

        rvResUser = findViewById(R.id.rvMisResenias);
        db = BuceoDB.getDatabase(this);
        resDao = db.ReseniaDAO();


        llm = new LinearLayoutManager(this);
        rvResUser.setLayoutManager(llm);
        actualizarLista();




    }

    @Override
    public void enviarReseniaElim(Resenia resenia) {
        resDao.deleteResenia(resenia);
        actualizarLista();
    }

    @Override
    public void enviarReseniaModif(Resenia resenia) {
        resDao.updateResenia(resenia);
        actualizarLista();
    }

    private void actualizarLista() {
        listaResenias = (ArrayList<Resenia>) resDao.selectByUserId(((BuceoApplication) getApplicationContext()).getUser().getId());

        adapter = new ResUserAdapter(listaResenias);
        rvResUser.setAdapter(adapter);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                res = listaResenias.get(rvResUser.getChildAdapterPosition(v));

                args.putInt(CLAVE_RESENIA, res.getId());
                MiReseniaDialog dialog = new MiReseniaDialog();
                dialog.setCancelable(false);
                dialog.setArguments(args);
                dialog.show(getSupportFragmentManager(), "MisReseniasDialog");
            }
        });

    }
}
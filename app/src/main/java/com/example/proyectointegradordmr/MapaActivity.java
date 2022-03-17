package com.example.proyectointegradordmr;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.proyectointegradordmr.dialog.OnAceptarReseniaListener;
import com.example.proyectointegradordmr.fragments.CentroFragment;
import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DAO.ReseniaDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Resenia;
import com.example.proyectointegradordmr.rvUtils.ReseniaAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.proyectointegradordmr.databinding.ActivityMapaBinding;

import java.util.ArrayList;

public class MapaActivity extends FragmentActivity implements View.OnClickListener, OnAceptarReseniaListener {

    private GoogleMap mMap;
    ImageView btnMenu;

    BuceoDB db;
    ReseniaDAO resDao;
    CentroDAO centroDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        btnMenu = findViewById(R.id.ivOpciones);
        btnMenu.setOnClickListener(this);

        db = BuceoDB.getDatabase(this);
        resDao = db.ReseniaDAO();
        centroDao = db.CentroDAO();

        Fragment fragment = new MapaFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.mapaFragment, fragment).commit();
    }


    @Override
    public void onClick(View v) {
        if(v.equals(btnMenu)) {
            PopupMenu menu = new PopupMenu(MapaActivity.this, btnMenu);

            menu.getMenuInflater().inflate(R.menu.menu_ppal, menu.getMenu());

            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.menuResenias) {
                        Intent i = new Intent(MapaActivity.this, MisReseniasActivity.class);
                        startActivity(i);
                    }



                    return true;
                }
            });

            menu.show();
        }

    }

    @Override
    public void enviarResenia(double calif, String resenia) {
        Resenia res = new Resenia(((BuceoApplication) getApplicationContext()).getIdCentro(), ((BuceoApplication) getApplicationContext()).getUser().getId(), calif, resenia);
        resDao.insertarResenia(res);

        Bundle args = new Bundle();

        args.putString(MapaFragment.CLAVE_NOMBRE_CENTRO, centroDao.selectCentroById(((BuceoApplication) getApplicationContext()).getIdCentro()).getNombreCentro());
        CentroFragment centroFragment = new CentroFragment();
        centroFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.mapaFragment, centroFragment).commit();

    }


}
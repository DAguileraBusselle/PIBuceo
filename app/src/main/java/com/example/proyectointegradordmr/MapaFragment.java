package com.example.proyectointegradordmr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Centro;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapaFragment extends Fragment {


    BuceoDB db;
    CentroDAO centroDao;
    ArrayList<Centro> listaCentros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);




        smf.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                db = BuceoDB.getDatabase(getContext());
                centroDao = db.CentroDAO();

                listaCentros = (ArrayList<Centro>) centroDao.selectAll();

                for (Centro centro : listaCentros) {
                    LatLng latLng = new LatLng(centro.getLatitud(), centro.getLongitud());
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(centro.getNombreCentro()));
                }

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        //TODO: abrir fragmento con detalles del centro
                        Toast.makeText(getContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });
            }
        });

        return view;
    }
}
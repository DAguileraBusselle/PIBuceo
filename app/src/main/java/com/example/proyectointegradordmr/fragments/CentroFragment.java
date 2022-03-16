package com.example.proyectointegradordmr.fragments;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectointegradordmr.BuceoApplication;
import com.example.proyectointegradordmr.MapaFragment;
import com.example.proyectointegradordmr.R;
import com.example.proyectointegradordmr.dialog.OnAceptarReseniaListener;
import com.example.proyectointegradordmr.dialog.ReseniaDialog;
import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DAO.InmersionDAO;
import com.example.proyectointegradordmr.room.DAO.ReseniaDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Centro;
import com.example.proyectointegradordmr.room.Entity.Inmersion;
import com.example.proyectointegradordmr.room.Entity.Resenia;
import com.example.proyectointegradordmr.rvUtils.InmersionAdapter;
import com.example.proyectointegradordmr.rvUtils.ReseniaAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class CentroFragment extends Fragment implements View.OnClickListener {

    public static final String CLAVE_ID_CENTRO = "ID CENTRO";
    BuceoDB db;
    CentroDAO centroDao;
    ReseniaDAO reseniaDao;
    InmersionDAO inmersDao;
    Centro centro;

    TextView tvNombreCentro;
    ImageView ivImgCentro;
    TextView tvCalif;
    RatingBar rbCalif;
    TextView tvInmerResen;
    TextView btnEscribirRes1;
    ImageView btnEscribirRes2;

    LinearLayoutManager llm;
    ReseniaAdapter resAdapter;
    InmersionAdapter inmAdapter;
    RecyclerView rvResenias;

    Boolean inmersiones = false;

    ArrayList<Resenia> listaResenias;
    ArrayList<Inmersion> listaInmersiones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_centro, container, false);

        db = BuceoDB.getDatabase(getContext());
        centroDao = db.CentroDAO();
        reseniaDao = db.ReseniaDAO();
        inmersDao = db.InmersionDAO();

        tvNombreCentro = view.findViewById(R.id.tvNombreCentro);
        ivImgCentro = view.findViewById(R.id.ivImgCentro);
        tvCalif = view.findViewById(R.id.tvCalifCentro);
        rbCalif = view.findViewById(R.id.rbCalifCentro);
        tvInmerResen = view.findViewById(R.id.tvInmerResen);
        btnEscribirRes1 = view.findViewById(R.id.tvEscribirResenia);


        String nombreCentro = getArguments().getString(MapaFragment.CLAVE_NOMBRE_CENTRO);
        centro = centroDao.selectCentroByNom(nombreCentro);

        ((BuceoApplication) getActivity().getApplicationContext()).setIdCentro(centro.getId());

        tvNombreCentro.setText(centro.getNombreCentro());

        rvResenias = view.findViewById(R.id.rvResenias);

        tvInmerResen.setOnClickListener(this);
        btnEscribirRes1.setOnClickListener(this);


        llm = new LinearLayoutManager(getContext());
        rvResenias.setLayoutManager(llm);
        listaResenias = (ArrayList<Resenia>) reseniaDao.selectByIdCentro(centro.getId());
        listaInmersiones = (ArrayList<Inmersion>) inmersDao.selectByIdCentro(centro.getId());

        inmAdapter = new InmersionAdapter(listaInmersiones);
        resAdapter = new ReseniaAdapter(listaResenias);
        rvResenias.setAdapter(resAdapter);

        //TODO: hacerlo concatenando el src
        int idImagen=0;
        switch (centro.getImagen()) {
            case "dive_college_centro":
                idImagen = R.drawable.dive_college_centro;
                break;
            case "la_graciosa_centro":
                idImagen = R.drawable.la_graciosa_centro;
                break;
            case "ocean_addicts_centro":
                idImagen = R.drawable.ocean_addicts_centro;
                break;
            case "revolution_dive_centro":
                idImagen = R.drawable.revolution_dive_centro;
                break;
        }


        ivImgCentro.setImageResource(idImagen);

        Float califMedia = centroDao.selectMediaCalifCentro(centro.getId());

        if(califMedia == null) {
            califMedia = 0f;
        }
        tvCalif.setText(String.valueOf(califMedia));
        rbCalif.setRating(califMedia);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(tvInmerResen)) {
            inmersiones = !inmersiones;

            if(inmersiones) {

                rvResenias.setAdapter(inmAdapter);

                tvInmerResen.setText(R.string.tv_ver_resenias);
            } else {
                rvResenias.setAdapter(resAdapter);

                tvInmerResen.setText(R.string.tv_ver_inmersiones);
            }
        } else {
            ReseniaDialog dialog = new ReseniaDialog();
            dialog.setCancelable(false);
            dialog.show(getActivity().getSupportFragmentManager(), "ReseniaDialog");

        }
    }

}

package com.example.proyectointegradordmr.rvUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectointegradordmr.R;
import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Resenia;

import java.util.ArrayList;

public class ResUserAdapter extends RecyclerView.Adapter<ResUserAdapter.ReseniaVH> implements View.OnClickListener {

    private ArrayList<Resenia> listaResenias;
    private View.OnClickListener listener;

    public ResUserAdapter(ArrayList<Resenia> datos){this.listaResenias = datos;}

    @NonNull
    @Override
    public ResUserAdapter.ReseniaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resenia_layout, parent, false);
        v.setOnClickListener(this);
        ResUserAdapter.ReseniaVH vh = new ResUserAdapter.ReseniaVH(v);
        return vh;
    }

    public void setListener(View.OnClickListener listener){this.listener = listener;}

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ResUserAdapter.ReseniaVH holder, int position) {
        holder.bindResenia(listaResenias.get(position));
    }

    @Override
    public int getItemCount() {
        return listaResenias.size();}


    public class ReseniaVH extends RecyclerView.ViewHolder {

        TextView tvNombreUser;
        TextView tvCalif;
        RatingBar rbCalif;
        TextView tvDesc;

        BuceoDB db;
        CentroDAO centroDao;

        public ReseniaVH(@NonNull View itemView) {
            super(itemView);

            db = BuceoDB.getDatabase(itemView.getContext());
            centroDao = db.CentroDAO();
            tvNombreUser = itemView.findViewById(R.id.tvNombreUserReseniaRV);
            rbCalif = itemView.findViewById(R.id.rbCalifRV);
            tvCalif = itemView.findViewById(R.id.tvCalifRV);
            tvDesc = itemView.findViewById(R.id.tvDescRV);


        }

        public void bindResenia(Resenia resenia) {
            tvNombreUser.setText(centroDao.selectCentroById(resenia.getIdCentro()).getNombreCentro());
            tvCalif.setText(String.valueOf(resenia.getCalif()));
            rbCalif.setRating((float) resenia.getCalif());
            tvDesc.setText(resenia.getDesc());



        }
    }
}

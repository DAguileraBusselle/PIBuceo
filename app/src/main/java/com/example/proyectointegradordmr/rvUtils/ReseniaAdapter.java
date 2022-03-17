package com.example.proyectointegradordmr.rvUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectointegradordmr.R;
import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Resenia;

import java.util.ArrayList;

public class ReseniaAdapter extends RecyclerView.Adapter<ReseniaAdapter.ReseniaVH> {

    private ArrayList<Resenia> listaResenias;

    public ReseniaAdapter(ArrayList<Resenia> datos){this.listaResenias = datos;}

    @NonNull
    @Override
    public ReseniaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resenia_layout, parent, false);
        ReseniaVH vh = new ReseniaVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReseniaAdapter.ReseniaVH holder, int position) {
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
        UsuarioDAO userDao;

        public ReseniaVH(@NonNull View itemView) {
            super(itemView);

            db = BuceoDB.getDatabase(itemView.getContext());
            userDao = db.UsuarioDAO();
            tvNombreUser = itemView.findViewById(R.id.tvNombreUserReseniaRV);
            rbCalif = itemView.findViewById(R.id.rbCalifRV);
            tvCalif = itemView.findViewById(R.id.tvCalifRV);
            tvDesc = itemView.findViewById(R.id.tvDescRV);


        }

        public void bindResenia(Resenia resenia) {
            tvNombreUser.setText(userDao.selectNombreById(resenia.getIdUser()));
            tvCalif.setText(String.valueOf(resenia.getCalif()));
            rbCalif.setRating((float) resenia.getCalif());
            tvDesc.setText(resenia.getDesc());



        }
    }
}

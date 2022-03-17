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
import com.example.proyectointegradordmr.room.Entity.Inmersion;
import com.example.proyectointegradordmr.room.Entity.Resenia;

import java.util.ArrayList;

public class InmersionAdapter extends RecyclerView.Adapter<InmersionAdapter.InmersVH> {

    private ArrayList<Inmersion> listaInmers;
    private View.OnClickListener listener;

    public InmersionAdapter(ArrayList<Inmersion> datos){this.listaInmers = datos;}

    @NonNull
    @Override
    public InmersionAdapter.InmersVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inmers_layout, parent, false);
        InmersionAdapter.InmersVH vh = new InmersionAdapter.InmersVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InmersionAdapter.InmersVH holder, int position) {
        holder.bindInmers(listaInmers.get(position));
    }

    @Override
    public int getItemCount() {
        return listaInmers.size();}


    public class InmersVH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvProfMax, tvProfMed, tvTempVer, tvTempInv, tvTiempo, tvDesc, tvFauna;


        public InmersVH(@NonNull View itemView) {
            super(itemView);

            tvNombre =  itemView.findViewById(R.id.tvNombreInmers);
            tvProfMax =  itemView.findViewById(R.id.tvProfMax);
            tvProfMed =  itemView.findViewById(R.id.tvProfMed);
            tvTempVer =  itemView.findViewById(R.id.tvTempVer);
            tvTempInv =  itemView.findViewById(R.id.tvTempInv);
            tvTiempo =  itemView.findViewById(R.id.tvTiempoMed);
            tvDesc =  itemView.findViewById(R.id.tvDescInmers);
            tvFauna =  itemView.findViewById(R.id.tvFaunaInmers);




        }

        public void bindInmers(Inmersion inmers) {
            tvNombre.setText(inmers.getNombre());
            tvProfMax.setText(inmers.getProfMax());
            tvProfMed.setText(inmers.getProfMed());
            tvTempVer.setText(inmers.getTempMediVeran());
            tvTempInv.setText(inmers.getTempMediInv());
            tvTiempo.setText(inmers.getTiempMed());
            tvDesc.setText(inmers.getDesc());
            tvFauna.setText(inmers.getFaunaComun());

        }
    }
}
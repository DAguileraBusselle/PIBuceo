package com.example.proyectointegradordmr.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.proyectointegradordmr.MisReseniasActivity;
import com.example.proyectointegradordmr.R;
import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DAO.ReseniaDAO;
import com.example.proyectointegradordmr.room.DB.BuceoDB;
import com.example.proyectointegradordmr.room.Entity.Resenia;

public class MiReseniaDialog extends DialogFragment {

    OnModificarReseniaListener modifListener;
    OnEliminarReseniaListener elimListener;
    TextView tvNombre;
    RatingBar rbCalif;
    TextView tvCalif;
    EditText etCalif;

    BuceoDB db;
    ReseniaDAO resDao;
    CentroDAO centroDao;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.resenia_dialog, null);
        tvNombre = v.findViewById(R.id.tvNombreCali);
        rbCalif = v.findViewById(R.id.rbCali);
        etCalif = v.findViewById(R.id.etCali);
        tvCalif = v.findViewById(R.id.tvCali);

        db = BuceoDB.getDatabase(getContext());
        resDao = db.ReseniaDAO();
        centroDao = db.CentroDAO();

        builder.setView(v);

        builder.setPositiveButton(R.string.fd_btn_modificar,null)
                .setNeutralButton(R.string.fd_btn_cancelar,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton(R.string.fd_btn_eliminar, null);

        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);

        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                int idRes = getArguments().getInt(MisReseniasActivity.CLAVE_RESENIA);
                Resenia res = resDao.selectById(idRes);
                tvNombre.setText(centroDao.selectCentroById(res.getIdCentro()).getNombreCentro());

                tvCalif.setText(String.valueOf(res.getCalif()));
                rbCalif.setRating((float) res.getCalif());
                etCalif.setText(res.getDesc());

                rbCalif.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        tvCalif.setText(String.valueOf(rbCalif.getRating()));
                    }

                });
                Button btnModif = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                btnModif.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        double calif = rbCalif.getRating();
                        String resenia = etCalif.getText().toString().trim();

                        res.setCalif(calif);
                        res.setDesc(resenia);

                        modifListener.enviarReseniaModif(res);
                        dialog.dismiss();

                    }
                });

                Button btnElim = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                btnElim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        elimListener.enviarReseniaElim(res);
                        dialog.dismiss();
                    }
                });
            }
        });

        return ad;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnModificarReseniaListener && context instanceof OnEliminarReseniaListener) {
            modifListener = (OnModificarReseniaListener) context;
            elimListener = (OnEliminarReseniaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnModificarReseniaListener and OnEliminarReseniaListener");
        }

    }

    @Override
    public void onDetach() {
        if (modifListener != null || elimListener != null) {
            modifListener = null;
            elimListener = null;
        }
        super.onDetach();
    }

}

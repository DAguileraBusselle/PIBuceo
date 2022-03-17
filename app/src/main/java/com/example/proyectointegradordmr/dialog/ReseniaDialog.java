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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.proyectointegradordmr.R;
import com.example.proyectointegradordmr.fragments.CentroFragment;
import com.google.android.material.snackbar.Snackbar;

public class ReseniaDialog extends DialogFragment {

    OnAceptarReseniaListener listener;
    RatingBar rbCalif;
    TextView tvCalif;
    EditText etCalif;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.resenia_dialog, null);
        rbCalif = v.findViewById(R.id.rbCali);
        etCalif = v.findViewById(R.id.etCali);
        tvCalif = v.findViewById(R.id.tvCali);


        builder.setView(v);


        builder.setPositiveButton(R.string.fd_btn_aceptar,null)
                .setNegativeButton(R.string.fd_btn_cancelar,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);

        ad.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                rbCalif.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        tvCalif.setText(String.valueOf(rbCalif.getRating()));
                    }

                });
                Button button = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        double calif = rbCalif.getRating();
                        String resenia = etCalif.getText().toString().trim();

                        listener.enviarResenia(calif,resenia);
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
        if (context instanceof OnAceptarReseniaListener) {
            listener = (OnAceptarReseniaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAceptarDatosListener ");
        }

    }

    @Override
    public void onDetach() {
        if (listener != null) {
            listener = null;
        }
        super.onDetach();
    }

}

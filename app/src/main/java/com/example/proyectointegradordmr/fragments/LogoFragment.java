package com.example.proyectointegradordmr.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.proyectointegradordmr.R;

public class LogoFragment extends Fragment {

    public  LogoFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.logo_fragment_layout, container, false);
    }
}

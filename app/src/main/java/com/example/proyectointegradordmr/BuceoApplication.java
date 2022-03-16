package com.example.proyectointegradordmr;

import android.app.Application;

import com.example.proyectointegradordmr.room.Entity.Usuario;

public class BuceoApplication extends Application {

    private Usuario user;
    private int idCentro;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Usuario getUser() { return user;}

    public void setUser(Usuario user) {
        this.user = user;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }
}

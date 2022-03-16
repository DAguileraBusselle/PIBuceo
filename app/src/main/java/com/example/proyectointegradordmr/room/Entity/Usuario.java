package com.example.proyectointegradordmr.room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "USUARIOS", indices = {@Index(value={"nombreUsuario"}, unique = true), @Index(value={"correo"}, unique = true)})
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nombreUsuario")
    public String nombreUsuario;
    @ColumnInfo(name = "correo")
    public String correo;
    @ColumnInfo(name = "password")
    public String password;

    public Usuario(String nombreUsuario, String correo, String password) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;


    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public int getId() { return id; }
}

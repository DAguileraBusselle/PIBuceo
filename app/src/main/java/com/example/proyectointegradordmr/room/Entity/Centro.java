package com.example.proyectointegradordmr.room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CENTROS_BUCEO")
public class Centro {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nombreCentro")
    public String nombreCentro;
    @ColumnInfo(name = "lat")
    public double latitud;
    @ColumnInfo(name = "long")
    public double longitud;

    public Centro(String nombreCentro, double latitud, double longitud) {
        this.nombreCentro = nombreCentro;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}

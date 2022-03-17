package com.example.proyectointegradordmr.room.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

import com.example.proyectointegradordmr.room.DAO.CentroDAO;
import com.example.proyectointegradordmr.room.DAO.InmersionDAO;
import com.example.proyectointegradordmr.room.DAO.ReseniaDAO;
import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.Entity.Centro;
import com.example.proyectointegradordmr.room.Entity.Inmersion;
import com.example.proyectointegradordmr.room.Entity.Resenia;
import com.example.proyectointegradordmr.room.Entity.Usuario;

@Database(entities = {Usuario.class, Centro.class, Inmersion.class, Resenia.class}, version = 5)
public abstract class BuceoDB extends RoomDatabase {
    public abstract UsuarioDAO UsuarioDAO();
    public abstract CentroDAO CentroDAO();
    public abstract InmersionDAO InmersionDAO();
    public abstract ReseniaDAO ReseniaDAO();


    private static BuceoDB BUCEOS_DB = null;

    public static BuceoDB getDatabase(Context context) {
        if (BUCEOS_DB == null) {
            BUCEOS_DB = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BuceoDB.class, "buceos_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return BUCEOS_DB;
    }
}
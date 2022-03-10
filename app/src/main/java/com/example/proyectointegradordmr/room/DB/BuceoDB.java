package com.example.proyectointegradordmr.room.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectointegradordmr.room.DAO.UsuarioDAO;
import com.example.proyectointegradordmr.room.Entity.Usuario;

@Database(entities = {Usuario.class}, version = 1)
public abstract class BuceoDB extends RoomDatabase {
    public abstract UsuarioDAO UsuarioDAO();

    private static BuceoDB BUCEOS_DB = null;

    public static BuceoDB getDatabase(Context context) {
        if (BUCEOS_DB == null) {
            BUCEOS_DB = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BuceoDB.class, "buceos_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return BUCEOS_DB;
    }
}
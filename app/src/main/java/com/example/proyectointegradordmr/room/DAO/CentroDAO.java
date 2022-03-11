package com.example.proyectointegradordmr.room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectointegradordmr.room.Entity.Centro;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CentroDAO {

    @Insert
    public void insertarCentros(Centro centro);

    @Query("SELECT * FROM CENTROS_BUCEO")
    public List<Centro> selectAll();

}

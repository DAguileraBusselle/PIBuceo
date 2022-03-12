package com.example.proyectointegradordmr.room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectointegradordmr.room.Entity.Resenia;

import java.util.List;

@Dao
public interface ReseniaDAO {

    @Query("SELECT * FROM RESENIAS")
    public List<Resenia> selectAll();

    @Insert
    public void insertarResenia(Resenia resen);

    @Delete
    public void deleteResenia(List<Resenia> listaResenias);
}

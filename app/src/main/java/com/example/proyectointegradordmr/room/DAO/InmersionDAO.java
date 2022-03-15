package com.example.proyectointegradordmr.room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectointegradordmr.room.Entity.Inmersion;
import com.example.proyectointegradordmr.room.Entity.Resenia;

import java.util.List;

@Dao
public interface InmersionDAO {

    @Query("SELECT * FROM INMERSIONES")
    public List<Inmersion> selectAll();

    @Query("SELECT * FROM INMERSIONES WHERE id_centro = :idCentro")
    public List<Inmersion> selectByIdCentro(int idCentro);

    @Insert
    public void insertarInmersiones(List<Inmersion> listaInmer);

    @Delete
    public void deleteInmersion(List<Inmersion> listaInmer);
}

package com.example.proyectointegradordmr.room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectointegradordmr.room.Entity.Centro;

import java.util.List;

@Dao
public interface CentroDAO {

    @Query("SELECT * FROM CENTROS_BUCEO WHERE id = :id")
    public Centro selectCentroById(int id);

    @Query("SELECT * FROM CENTROS_BUCEO WHERE nombreCentro = :nom")
    public Centro selectCentroByNom(String nom);

    @Query("SELECT AVG(calificacion) FROM RESENIAS WHERE id_centro = :idCentro")
    public Float selectMediaCalifCentro(int idCentro);

    @Insert
    public void insertarCentros(Centro centro);

    @Query("SELECT * FROM CENTROS_BUCEO")
    public List<Centro> selectAll();

}

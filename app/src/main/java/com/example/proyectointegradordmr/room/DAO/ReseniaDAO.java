package com.example.proyectointegradordmr.room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectointegradordmr.room.Entity.Resenia;
import com.example.proyectointegradordmr.room.Entity.Usuario;

import java.util.List;

@Dao
public interface ReseniaDAO {

    @Query("SELECT * FROM RESENIAS")
    public List<Resenia> selectAll();

    @Query("SELECT * FROM RESENIAS WHERE id = :id")
    public Resenia selectById(int id);

    @Query("SELECT * FROM RESENIAS WHERE id_centro = :idCentro")
    public List<Resenia> selectByIdCentro(int idCentro);

    @Query("SELECT * FROM RESENIAS WHERE id_usuario = :idUser")
    public List<Resenia> selectByUserId(int idUser);

    @Insert
    public void insertarResenia(Resenia resen);

    @Update
    public void updateResenia(Resenia res);

    @Delete
    public void deleteResenia(Resenia res);

    @Query("SELECT * FROM RESENIAS WHERE id_centro = :idCentro AND id_usuario = :idUser")
    public Resenia comprobarRes(int idCentro, int idUser);


}

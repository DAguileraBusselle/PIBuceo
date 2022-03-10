package com.example.proyectointegradordmr.room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyectointegradordmr.room.Entity.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Insert
    public void insertarUsuario(Usuario user);

    @Query("SELECT * FROM USUARIOS WHERE nombreUsuario = :nomUser")
    public Usuario comprobarNombre(String nomUser);

    @Query("SELECT * FROM USUARIOS WHERE correo = :correo")
    public Usuario comprobarCorreo(String correo);

    //INICIAR SESION
    @Query("SELECT password FROM USUARIOS WHERE nombreUsuario = :nomUser")
    public String comprobarSignInNombre(String nomUser);

    @Query("SELECT password FROM USUARIOS WHERE correo = :correo")
    public String comprobarSignInCorreo(String correo);

    //CHECK
    @Query("SELECT * FROM USUARIOS")
    public List<Usuario> check();

}
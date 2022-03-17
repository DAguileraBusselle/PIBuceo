package com.example.proyectointegradordmr.room.Entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "RESENIAS", foreignKeys = {@ForeignKey(entity = Centro.class,
        parentColumns = "id",
        childColumns = "id_centro",
        onDelete = ForeignKey.CASCADE), @ForeignKey(entity = Usuario.class,
        parentColumns = "id",
        childColumns = "id_usuario",
        onDelete = ForeignKey.CASCADE)}, indices = {@Index("id_centro"), @Index("id_usuario")})
public class Resenia {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "id_centro")
    public int idCentro;
    @ColumnInfo(name = "id_usuario")
    public int idUser;
    @ColumnInfo(name = "calificacion")
    public double calif;
    @ColumnInfo(name = "descripcion")
    public String desc;

    public Resenia(int idCentro, int idUser, double calif, String desc) {
        this.idCentro = idCentro;
        this.idUser = idUser;
        this.calif = calif;
        this.desc = desc;
    }

    public int getId() { return id; }

    public int getIdCentro() {
        return idCentro;
    }

    public int getIdUser() {
        return idUser;
    }

    public double getCalif() {
        return calif;
    }

    public String getDesc() {
        return desc;
    }

    public void setCalif(double calif) {
        this.calif = calif;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

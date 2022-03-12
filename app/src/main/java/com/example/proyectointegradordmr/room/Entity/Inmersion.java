package com.example.proyectointegradordmr.room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "INMERSIONES", foreignKeys = {@ForeignKey(entity = Centro.class,
        parentColumns = "id",
        childColumns = "id_centro",
        onDelete = ForeignKey.CASCADE)})
public class Inmersion {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "id_centro")
    public int idCentro;
    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "descripcion")
    public String desc;
    @ColumnInfo(name = "profun_max")
    public String profMax;
    @ColumnInfo(name = "profun_media")
    public String profMed;
    @ColumnInfo(name = "tiempo_medio")
    public String tiempMed;
    @ColumnInfo(name = "dificultad")
    public String dif;
    @ColumnInfo(name = "titulo_recom")
    public String tituloRecom;
    @ColumnInfo(name = "temp_medi_verano")
    public String tempMediVeran;
    @ColumnInfo(name = "temp_medi_invierno")
    public String getTempMediInv;
    @ColumnInfo(name = "fauna_comun")
    public String faunaComun;

    public Inmersion(int idCentro, String nombre, String desc,
                     String profMax, String profMed, String tiempMed,
                     String dif, String tituloRecom, String tempMediVeran,
                     String getTempMediInv, String faunaComun) {
        this.idCentro = idCentro;
        this.nombre = nombre;
        this.desc = desc;
        this.profMax = profMax;
        this.profMed = profMed;
        this.tiempMed = tiempMed;
        this.dif = dif;
        this.tituloRecom = tituloRecom;
        this.tempMediVeran = tempMediVeran;
        this.getTempMediInv = getTempMediInv;
        this.faunaComun = faunaComun;
    }

    public int getId() {
        return id;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDesc() {
        return desc;
    }

    public String getProfMax() {
        return profMax;
    }

    public String getProfMed() {
        return profMed;
    }

    public String getTiempMed() {
        return tiempMed;
    }

    public String getDif() {
        return dif;
    }

    public String getTituloRecom() {
        return tituloRecom;
    }

    public String getTempMediVeran() {
        return tempMediVeran;
    }

    public String getGetTempMediInv() {
        return getTempMediInv;
    }

    public String getFaunaComun() {
        return faunaComun;
    }
}

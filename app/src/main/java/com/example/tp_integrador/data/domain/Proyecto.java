package com.example.tp_integrador.data.domain;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Proyecto implements Parcelable {

    private Integer idProyecto;
    private Ong ong;
    private String nombre;
    private String descripcion;
    private String objetivos; //necesidades
    private String disponibilidad;
    private String ubicacion;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto, Ong ong, String nombre, String descripcion, String objetivos, String ubicacion,String disponibilidad) {
        this.idProyecto = idProyecto;
        this.ong = ong;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.objetivos = objetivos;
        this.ubicacion = ubicacion;
        this.disponibilidad = disponibilidad;
    }

    protected Proyecto(Parcel in) {
        if (in.readByte() == 0) {
            idProyecto = null;
        } else {
            idProyecto = in.readInt();
        }
        nombre = in.readString();
        descripcion = in.readString();
        objetivos = in.readString();
        disponibilidad = in.readString();
        ubicacion = in.readString();
    }

    public static final Creator<Proyecto> CREATOR = new Creator<Proyecto>() {
        @Override
        public Proyecto createFromParcel(Parcel in) {
            return new Proyecto(in);
        }

        @Override
        public Proyecto[] newArray(int size) {
            return new Proyecto[size];
        }
    };

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (idProyecto == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idProyecto);
        }
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(objetivos);
        dest.writeString(disponibilidad);
        dest.writeString(ubicacion);
    }
}

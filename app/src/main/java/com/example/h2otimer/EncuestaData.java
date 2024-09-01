package com.example.h2otimer;

import android.os.Parcel;
import android.os.Parcelable;

public class EncuestaData implements Parcelable {
    private String opcionSeleccionada;
    private long tiempoEnMilisegundos;

    public EncuestaData(String opcionSeleccionada, long tiempoEnMilisegundos) {
        this.opcionSeleccionada = opcionSeleccionada;
        this.tiempoEnMilisegundos = tiempoEnMilisegundos;
    }

    protected EncuestaData(Parcel in) {
        opcionSeleccionada = in.readString();
        tiempoEnMilisegundos = in.readLong();
    }

    public static final Creator<EncuestaData> CREATOR = new Creator<EncuestaData>() {
        @Override
        public EncuestaData createFromParcel(Parcel in) {
            return new EncuestaData(in);
        }

        @Override
        public EncuestaData[] newArray(int size) {
            return new EncuestaData[size];
        }
    };

    public String getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    public void setOpcionSeleccionada(String opcionSeleccionada) {
        this.opcionSeleccionada = opcionSeleccionada;
    }

    public long getTiempoEnMilisegundos() {
        return tiempoEnMilisegundos;
    }

    public void setTiempoEnMilisegundos(long tiempoEnMilisegundos) {
        this.tiempoEnMilisegundos = tiempoEnMilisegundos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(opcionSeleccionada);
        dest.writeLong(tiempoEnMilisegundos);
    }
}

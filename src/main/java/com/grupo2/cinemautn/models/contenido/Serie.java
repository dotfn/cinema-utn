package com.grupo2.cinemautn.models.contenido;

import com.grupo2.cinemautn.models.calificacion.Calificacion;
import javafx.scene.image.Image;

import java.util.List;

public class Serie extends Contenido {
    private int temporadas;
    private int episodios;

    // constructor
    public Serie(String titulo, Genero genero, int anio, String director, int temporadas, int episodios) {
        super(titulo, genero, anio, director);
        this.temporadas = temporadas;
        this.episodios = episodios;
    }

    public Serie(){
        super();
    }

    public int getTemporadas() { return temporadas; }
    public int getEpisodios() { return episodios; }

    @Override
    public String toString() {
        return "Serie: " + super.toString() + " - " + temporadas + " temporadas, " + episodios + " episodios";
    }
}

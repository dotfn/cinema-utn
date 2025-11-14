package com.grupo2.cinemautn.models.contenido;

import com.grupo2.cinemautn.models.resena.Resena;

import java.util.ArrayList;
import java.util.Objects;

public class Serie extends Contenido {
    private int temporadas;
    private int episodios;

    // constructor

    // Constructor que incluye imagenPortada
    public Serie(String titulo, Genero genero, int anio, String director, String imagenPortada, ArrayList<Resena> resenas, int temporadas, int episodios) {
        super(titulo, genero, anio, director, imagenPortada, resenas);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Serie serie = (Serie) o;
        return temporadas == serie.temporadas && episodios == serie.episodios;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), temporadas, episodios);
    }
}

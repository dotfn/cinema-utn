package com.grupo2.cinemautn.models.contenido;

import com.grupo2.cinemautn.models.resena.Resena;

import java.util.ArrayList;
import java.util.Objects;

public class Pelicula extends Contenido {
    //Atributos
    private double duracion; // en horas

    //Construtor

    // Constructor que incluye imagenPortada
    public Pelicula(String titulo, Genero genero, int anio, String director, String imagenPortada, ArrayList<Resena> resenas, double duracion) {
        super(titulo, genero, anio, director, imagenPortada, resenas);
        this.duracion = duracion;
    }

    public Pelicula(){
        super();
    }

    public double getDuracion() { return duracion; }

    @Override
    public String toString() {
        return "Pelicula: " + super.toString() + " - Duración: " + duracion + " horas";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pelicula pelicula = (Pelicula) o;
        return Double.compare(duracion, pelicula.duracion) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duracion);
    }
}

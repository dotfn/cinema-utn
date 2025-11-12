package com.grupo2.cinemautn.models.contenido;

public class Pelicula extends Contenido {
    //Atributos
    private double duracion; // en horas

    //Construtor
    public Pelicula(String titulo, Genero genero, int anio, String director, double duracion) {
        super(titulo, genero, anio, director);
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
}

package com.grupo2.cinemautn.models.contenido;

import java.util.ArrayList;
import java.util.List;

public abstract class Contenido {
    protected int id;
    protected String titulo;
    protected Genero genero;
    protected int anio;
    protected String director;
    protected List<Calificacion> calificaciones = new ArrayList<>();

    public Contenido(int id, String titulo, Genero genero, int anio, String director) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.director = director;
    }
    public Contenido(){
        super();
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public Genero getGenero() { return genero; }
    public int getAnio() { return anio; }
    public String getDirector() { return director; }

    public void agregarCalificacion(Calificacion calificacion) {
        calificaciones.add(calificacion);
    }

    public double promedioCalificaciones() {
        if (calificaciones.isEmpty()) return 0;
        double total = 0;
        for (Calificacion c : calificaciones) {
            total += c.getEstrellas();
        }
        return total / calificaciones.size();
    }

    @Override
    public String toString() {
        return titulo + " (" + anio + ") - Dirigido por " + director;
    }
}

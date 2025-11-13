package com.grupo2.cinemautn.models.contenido;

import com.grupo2.cinemautn.models.calificacion.Calificacion;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Contenido {
    private static int contador = 0;
    private int id;
    protected String titulo;
    protected Genero genero;
    protected int anio;
    protected String director;
    protected boolean estado;
    protected List<Calificacion> calificaciones = new ArrayList<>();
    // nuevo atributo

    public Contenido() {
        contador ++;
        this.id = contador;
        this.estado = true;
    }

    public Contenido(String titulo, Genero genero, int anio, String director) {
        contador ++;
        this.id = contador;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.director = director;
        this.estado = true;
    }

    public Contenido(String titulo, Genero genero, int anio, String director, List<Calificacion> calificaciones) {
        contador ++;
        this.id = contador;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.director = director;
        this.estado = true;
        this.calificaciones = calificaciones;
    }

    // setters y getters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Genero getGenero() { return genero; }

    public void setGenero(Genero genero) { this.genero = genero; }

    public int getAnio() { return anio; }

    public void setAnio(int anio) { this.anio = anio; }

    public String getDirector() { return director; }

    public void setDirector(String director) { this.director = director; }

    public boolean isEstado() { return estado; }

    public void setEstado(boolean estado) { this.estado = estado; }

    public List<Calificacion> getCalificaciones() { return calificaciones; }

    public void setCalificaciones(List<Calificacion> calificaciones) { this.calificaciones = calificaciones; }

    public void agregarCalificacion(Calificacion calificacion) { calificaciones.add(calificacion); }

    // metodos

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

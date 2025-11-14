package com.grupo2.cinemautn.models.contenido;

import com.grupo2.cinemautn.models.resena.Resena;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Contenido {
    private static int contador = 0;
    private int id;
    protected String titulo;
    protected Genero genero;
    protected int anio;
    protected String director;
    protected boolean estado;
    protected List<Resena> resenas;
    protected String imagenPortada; // ruta o nombre de la imagen (String)

    public Contenido() {
        contador ++;
        this.id = contador;
        this.estado = true;
    }

    public Contenido(String titulo, Genero genero, int anio, String director, String imagenPortada, ArrayList<Resena> resenas) {
        contador ++;
        this.id = contador;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.director = director;
        this.estado = true;
        this.imagenPortada = imagenPortada;
        this.resenas = resenas;
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

    public List<Resena> getResenas() { return resenas; }

    public void setResenas(List<Resena> resenas) { this.resenas = resenas; }

    public String getImagenPortada() { return imagenPortada; }

    public void setImagenPortada(String imagenPortada) { this.imagenPortada = imagenPortada; }

    // metodos
    public double promedioResenas() {
        if (resenas.isEmpty()) return 0;
        double total = 0;
        for (Resena c : resenas) {
            total += c.getEstrellas();
        }
        return total / resenas.size();
    }

    @Override
    public String toString() {
        String base = titulo + " (" + anio + ") - Dirigido por " + director;
        if (imagenPortada != null && !imagenPortada.isEmpty()) {
            base += " - Portada: " + imagenPortada;
        }
        return base;
    }


    public void agregarResena(Resena resena) { resenas.add(resena); }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contenido contenido = (Contenido) o;
        return id == contenido.id && anio == contenido.anio && estado == contenido.estado && Objects.equals(titulo, contenido.titulo) && genero == contenido.genero && Objects.equals(director, contenido.director) && Objects.equals(resenas, contenido.resenas) && Objects.equals(imagenPortada, contenido.imagenPortada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, genero, anio, director, estado, resenas, imagenPortada);
    }
}

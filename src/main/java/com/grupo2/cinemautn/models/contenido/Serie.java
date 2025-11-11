package com.grupo2.cinemautn.models.contenido;

public class Serie extends Contenido {
    private int temporadas;
    private int episodios;

    public Serie(int id, String titulo, Genero genero, int anio, String director, int temporadas, int episodios) {
        super(id, titulo, genero, anio, director);
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

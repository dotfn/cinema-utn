package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.models.contenido.Pelicula;
import com.grupo2.cinemautn.models.contenido.Serie;
import com.grupo2.cinemautn.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class PersistenceService {
    private final GestoraUsuariosJSON gestoraUsuarios = new GestoraUsuariosJSON();
    private final GestoraPeliculasJSON gestoraPeliculas = new GestoraPeliculasJSON();
    private final GestoraSeriesJSON gestoraSeries = new GestoraSeriesJSON();

    private final ArrayList<Usuario> usuarios;
    private final ArrayList<Pelicula> peliculas;
    private final ArrayList<Serie> series;

    public PersistenceService(ArrayList<Usuario> usuarios, ArrayList<Pelicula> peliculas, ArrayList<Serie> series) {
        this.usuarios = new ArrayList<>();
        this.peliculas = new ArrayList<>();
        this.series = new ArrayList<>();
    }

    public void guardarDatosJSON() {
        gestoraUsuarios.listaToArchivo(usuarios);
        gestoraPeliculas.listaToArchivo(peliculas);
        gestoraSeries.listaToArchivo(series);
    }

    public void cargarDatosJSON() {
        // Obtener primero las listas desde los archivos
        List<Usuario> usuariosFromFile = gestoraUsuarios.archivoALista();
        List<Pelicula> peliculasFromFile = gestoraPeliculas.archivoALista();
        List<Serie> seriesFromFile = gestoraSeries.archivoALista();

        // Limpiar las listas actuales
        usuarios.clear();
        peliculas.clear();
        series.clear();

        // Añadir solo si la lectura devolvió una lista no nula
        if (usuariosFromFile != null) {
            usuarios.addAll(usuariosFromFile);
        }
        if (peliculasFromFile != null) {
            peliculas.addAll(peliculasFromFile);
        }
        if (seriesFromFile != null) {
            series.addAll(seriesFromFile);
        }
    }
}
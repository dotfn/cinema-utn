package com.grupo2.cinemautn.models.usuarios;

import com.grupo2.cinemautn.models.contenido.Contenido;

import java.util.HashSet;

public class ListaFavoritos {
    //atributos
    private HashSet<Contenido> favoritos;

    //constructor
    public ListaFavoritos() {
        this.favoritos = new HashSet<>();
    }

    //metodos
    public void agregarFavorito(Contenido contenido) {
        favoritos.add(contenido);
    }

    public void eliminarFavorito(Contenido contenido) {
        favoritos.remove(contenido);
    }

    public HashSet<Contenido> getFavoritos() {
        return favoritos;
    }
}

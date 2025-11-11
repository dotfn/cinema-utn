package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.exceptions.ContenidoNoEncontradoException;
import com.grupo2.cinemautn.interfaces.IGestionable;
import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.models.contenido.Genero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContenidoService implements IGestionable<Contenido> {
    private Map<Integer, Contenido> contenidos = new HashMap<>();

    @Override
    public void crear(Contenido c) {
        contenidos.put(c.getId(), c);
    }

    @Override
    public Contenido leer(int id) {
        Contenido contenido = contenidos.get(id);
        if (contenido == null) {
            throw new ContenidoNoEncontradoException("Contenido con ID " + id + " no encontrado.");
        }
        return contenido;
    }

    @Override
    public void actualizar(Contenido c) {
        if (!contenidos.containsKey(c.getId())) {
            throw new ContenidoNoEncontradoException("No se puede actualizar, contenido no encontrado.");
        }
        contenidos.put(c.getId(), c);
    }

    @Override
    public void eliminar(int id) {
        if (!contenidos.containsKey(id)) {
            throw new ContenidoNoEncontradoException("No se puede eliminar, contenido no encontrado.");
        }
        contenidos.remove(id);
    }

    @Override
    public List<Contenido> listar() {
        return new ArrayList<>(contenidos.values());
    }

    public List<Contenido> buscarPorGenero(Genero genero) {
        List<Contenido> resultado = new ArrayList<>();
        for (Contenido c : contenidos.values()) {
            if (c.getGenero() == genero) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

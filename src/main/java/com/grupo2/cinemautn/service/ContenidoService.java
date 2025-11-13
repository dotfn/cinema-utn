package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.exceptions.ContenidoNoEncontradoException;
import com.grupo2.cinemautn.interfaces.ABMCL;
import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.persistence.GestoraContenidoJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContenidoService implements ABMCL<Contenido> {
    private Map<Integer, Contenido> contenidos = new HashMap<>();
    private GestoraContenidoJSON gestoraContenidoJSON = new GestoraContenidoJSON();

    // Constructor: cargar desde archivo
    public ContenidoService() {
        // cargar lista desde la gestora y poblar el mapa
        List<Contenido> lista = gestoraContenidoJSON.archivoALista();
        if (lista != null) {
            for (Contenido c : lista) {
                if (c != null) {
                    contenidos.put(c.getId(), c);
                }
            }
        }
    }

    // interfaz ABMCL
    @Override
    public void alta(Contenido c) {
        if (c == null) return;
        if (!contenidos.containsKey(c.getId())) {
            contenidos.put(c.getId(), c);
            // Persistir cambios
            gestoraContenidoJSON.listaToArchivo(new ArrayList<>(contenidos.values()));
        } else {
            throw new IllegalArgumentException("El contenido con ID " + c.getId() + " ya existe.");
        }
    }

    @Override
    public void baja(int id) {
        if (!contenidos.containsKey(id)) {
            throw new ContenidoNoEncontradoException("No se puede eliminar, contenido no encontrado.");
        }

        // implementación de baja lógica
        contenidos.get(id).setEstado(false);
        // Persistir cambios
        gestoraContenidoJSON.listaToArchivo(new ArrayList<>(contenidos.values()));
    }

    @Override
    public Contenido consulta(int id) {
        Contenido contenido = contenidos.get(id);
        if (contenido == null) {
            throw new ContenidoNoEncontradoException("Contenido con ID " + id + " no encontrado.");
        }
        return contenido;
    }

    @Override
    public void modificacion(Contenido c) {
        if (c == null) return;
        if (!contenidos.containsKey(c.getId())) {
            throw new ContenidoNoEncontradoException("No se puede actualizar, contenido no encontrado.");
        }
        contenidos.put(c.getId(), c);
        // Persistir cambios
        gestoraContenidoJSON.listaToArchivo(new ArrayList<>(contenidos.values()));
    }

    @Override
    public List<Contenido> listar() {
        return new ArrayList<>(contenidos.values());
    }
}

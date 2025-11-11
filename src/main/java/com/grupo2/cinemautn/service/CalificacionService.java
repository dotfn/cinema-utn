package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.interfaces.IGestionable;
import com.grupo2.cinemautn.models.contenido.Calificacion;

import java.util.ArrayList;
import java.util.List;

public class CalificacionService implements IGestionable<Calificacion> {

    private List<Calificacion> calificaciones;

    public CalificacionService() {
        this.calificaciones = new ArrayList<>();
    }

    @Override
    public void crear(Calificacion c) {
        calificaciones.add(c);
    }

    @Override
    public Calificacion leer(int id) {
        for (Calificacion c : calificaciones) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Calificacion c) {
        for (int i = 0; i < calificaciones.size(); i++) {
            if (calificaciones.get(i).getId() == c.getId()) {
                calificaciones.set(i, c);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        calificaciones.removeIf(c -> c.getId() == id);
    }

    @Override
    public List<Calificacion> listar() {
        return new ArrayList<>(calificaciones);
    }

    // Métodos adicionales específicos

    public List<Calificacion> filtrarPorContenido(int idContenido) {
        List<Calificacion> resultado = new ArrayList<>();
        for (Calificacion c : calificaciones) {
            if (c.getIdContenido() == idContenido) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Calificacion> filtrarPorUsuario(int idUsuario) {
        List<Calificacion> resultado = new ArrayList<>();
        for (Calificacion c : calificaciones) {
            if (c.getIdUsuario() == idUsuario) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

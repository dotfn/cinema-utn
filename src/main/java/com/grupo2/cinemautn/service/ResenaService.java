package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.interfaces.ABMCL;
import com.grupo2.cinemautn.models.resena.Resena;

import java.util.ArrayList;
import java.util.List;

public class ResenaService implements ABMCL<Resena> {

    private final ArrayList<Resena> resenas;

    public ResenaService() {
        this.resenas = new ArrayList<>();
    }

    @Override
    public void alta(Resena c) {
        resenas.add(c);
    }

    @Override
    public void baja(int id) {
        resenas.removeIf(c -> c.getId() == id);
    }

    @Override
    public void modificacion(Resena c) {
        for (int i = 0; i < resenas.size(); i++) {
            if (resenas.get(i).getId() == c.getId()) {
                resenas.set(i, c);
                return;
            }
        }
    }

    @Override
    public Resena consulta(int id) {
        for (Resena c : resenas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Resena> listar() {
        return new ArrayList<>(resenas);
    }

    // Métodos adicionales específicos

    public List<Resena> filtrarPorUsuario(int idUsuario) {
        List<Resena> resultado = new ArrayList<>();
        for (Resena c : resenas) {
            if (c.getIdUsuario() == idUsuario) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

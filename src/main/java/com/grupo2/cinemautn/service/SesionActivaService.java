package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.models.usuarios.Usuario;

/**
 * Servicio simple que mantiene la sesión del usuario actualmente logueado.
 * Implementación singleton básica y minimalista para uso académico.
 */
public class SesionActivaService {
    private static SesionActivaService instance;
    private Usuario usuario;

    private SesionActivaService() { }

    public static synchronized SesionActivaService getInstance() {
        if (instance == null) {
            instance = new SesionActivaService();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void clear() {
        this.usuario = null;
    }
}

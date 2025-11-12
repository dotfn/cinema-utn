package com.grupo2.cinemautn.service;

/**
 * Proporciona instancias compartidas de servicios de la aplicación.
 * Evita crear múltiples instancias de UsuarioService/AuthService en distintos controladores.
 */
public class UsuarioActivoService {
    public static final UsuarioService usuarioService = new UsuarioService();
    public static final AuthService authService = new AuthService(usuarioService);
}


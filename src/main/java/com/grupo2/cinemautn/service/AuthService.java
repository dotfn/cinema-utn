package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.interfaces.IGestionable;
import com.grupo2.cinemautn.models.usuarios.Usuario;

import java.util.List;

public class AuthService implements IGestionable<Usuario> {

    private Usuario usuarioActual;
    private UsuarioService usuarioService;

    public AuthService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Autenticación simple: buscar por correo y comparar contrasena
    public Usuario iniciarSesion(String correo, String contrasena) {
        if (correo == null || contrasena == null) return null;
        Usuario u = usuarioService.buscarPorCorreo(correo);
        if (u != null && contrasena.equals(u.getContrasena())) {
            this.usuarioActual = u;
            return u;
        }
        return null;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }

    // Métodos IGestionable (delegan a usuarioService cuando procede)
    @Override
    public void crear(Usuario c) {
        usuarioService.crear(c);
    }

    @Override
    public Usuario leer(int id) {
        return usuarioService.leer(id);
    }

    @Override
    public void actualizar(Usuario c) {
        usuarioService.actualizar(c);
    }

    @Override
    public void eliminar(int id) {
        usuarioService.eliminar(id);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioService.listar();
    }
}

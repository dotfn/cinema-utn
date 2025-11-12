package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.interfaces.IGestionable;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.persistence.GestoraUsuariosJSON;

import java.util.*;

public class UsuarioService implements IGestionable<Usuario> {
    private final Set<Usuario> usuarios = new HashSet<>();
    private final GestoraUsuariosJSON gestora = new GestoraUsuariosJSON();

    public UsuarioService() {
        // cargar desde archivo si existe
        List<Usuario> lista = gestora.archivoALista();
        if (lista != null) {
            usuarios.addAll(lista);
        }
    }

    private void guardarUsuario() {
        // convertir set a ArrayList para la gestora
        gestora.listaToArchivo(new ArrayList<>(usuarios));
    }

    @Override
    public void crear(Usuario u) {
        if (u == null) return;
        usuarios.add(u);
        guardarUsuario();
    }

    @Override
    public Usuario leer(int id) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == id) return u;
        }
        return null;
    }

    @Override
    public void actualizar(Usuario u) {
        if (u == null) return;
        Usuario existente = leer(u.getIdUsuario());
        if (existente != null) {
            System.out.println("Cambiando usuario: " + existente + " por " + u);

            usuarios.remove(existente);
            usuarios.add(u);
            guardarUsuario();
        }
    }

    @Override
    public void eliminar(int id) {
        Usuario u = leer(id);
        if (u != null) {
            usuarios.remove(u);
            guardarUsuario();
        }
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarPorCorreo(String correo) {
        if (correo == null) return null;
        for (Usuario u : usuarios) {
            if (correo.equalsIgnoreCase(u.getEmail())) return u;
        }
        return null;
    }
}

package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.interfaces.ABMCL;
import com.grupo2.cinemautn.models.usuarios.Rol;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.persistence.GestoraUsuariosJSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UsuarioService implements ABMCL<Usuario> {
    //atributos
    private GestoraUsuariosJSON gestoraUsuariosJSON = new GestoraUsuariosJSON();
    private HashSet<Usuario> usuarios;


    //constructor
    public UsuarioService() {
        this.usuarios = new HashSet<>(gestoraUsuariosJSON.archivoALista());
    }

    // métodos auxiliares
    public boolean verificarEmail(String email) {
        // verificar que el email no exista en la lista de usuarios
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail() != null && usuario.getEmail().equalsIgnoreCase(email)) {
                System.out.println("El email ya existe: " + email);
                return false; //el email ya existe
            }
        }

        // verificar que el email tenga un formato válido (básico)
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex); //formato inválido
    }

    /*
    // versiones de conveniencia (mantener compatibilidad con llamadas existentes)
    public void alta(String nombre, String email, String contrasena, Rol rol) {
        if (verificarEmail(email)) {
            Usuario nuevoUsuario = new Usuario(nombre, email, contrasena, rol);
            usuarios.add(nuevoUsuario);
        }
    }

    public void baja(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(email)) {
                u.setEstado(false);
                return;
            }
        }
    }

    public void modificacion(String email, String nuevoNombre, String nuevaContrasena) {
        for (Usuario u : usuarios) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(email)) {
                u.setNombre(nuevoNombre);
                u.setContrasena(nuevaContrasena);
                return;
            }
        }
    }

    public Usuario consulta(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }
    */

    // Implementación de la interfaz ABMCL
    @Override
    public void alta(Usuario c) {
        if (c == null) return;
        if (verificarEmail(c.getEmail())) {
            usuarios.add(c);
        }
    }

    @Override
    public void baja(int id) {
        for (Usuario u : usuarios) {
            if (u != null && u.getIdUsuario() == id) {
                u.setEstado(false);
                return;
            }
        }
    }

    @Override
    public Usuario consulta(int id) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void modificacion(Usuario c) {
        if (c == null) return;
        int id = c.getIdUsuario();
        for (Usuario usuarioExistente : usuarios) {
            if (usuarioExistente.getIdUsuario() == id) {
                // actualizar campos relevantes
                if (c.getNombre() != null) usuarioExistente.setNombre(c.getNombre());
                if (c.getContrasena() != null) usuarioExistente.setContrasena(c.getContrasena());
                if (c.getRol() != null) usuarioExistente.setRol(c.getRol());
                return;
            }
        }
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }
}

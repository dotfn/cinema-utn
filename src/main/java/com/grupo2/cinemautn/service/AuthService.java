package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.persistence.GestoraUsuariosJSON;

import java.util.ArrayList;

public class AuthService {

    private final GestoraUsuariosJSON gestoraUsuarios = new GestoraUsuariosJSON();
    private final String ARCHIVO_USUARIOS = "usuarios.json";

    /**
     * Intenta autenticar un usuario por email y contraseña.
     * @param email correo del usuario
     * @param contrasena contraseña en texto claro (en este proyecto no hay hashing aún)
     * @return el objeto Usuario si las credenciales son válidas y el usuario está activo; null si no coincide
     */
    public Usuario authenticate(String email, String contrasena) {
        if (email == null || contrasena == null) return null;
        ArrayList<Usuario> usuarios = gestoraUsuarios.archivoALista(ARCHIVO_USUARIOS);
        if (usuarios == null) return null;

        for (Usuario u : usuarios) {
            if (u == null) continue;
            String uEmail = u.getEmail();
            String uPass = u.getContrasena();
            boolean activo = u.isEstado();
            if (uEmail != null && uEmail.equalsIgnoreCase(email) && uPass != null && uPass.equals(contrasena) && activo) {
                return u;
            }
        }

        return null;
    }

}

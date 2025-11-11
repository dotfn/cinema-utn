package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.models.usuarios.Rol;
import com.grupo2.cinemautn.models.usuarios.Usuario;

import java.util.HashSet;

public class UsuarioService {
    //atributos
    private HashSet<Usuario> usuarios;

    //constructor
    public UsuarioService() {
        this.usuarios = new HashSet<>();
    }

    //metodos
    public boolean verificarEmail(String email){
        for (Usuario usuario : usuarios){
            if (usuario.getEmail().equalsIgnoreCase(email)){
                return false; //el email ya existe
            }
        }
        return true; //el email no existe
    }

    public void crear(String nombre, String email, String contrasena, Rol rol){
        if (verificarEmail(email)){
            Usuario nuevoUsuario = new Usuario(nombre, email, contrasena, rol);
            usuarios.add(nuevoUsuario);
        }
    }

    public void eliminar(String email){
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                u.setEstado(false);
                return;
            }
        }
    }

    public void modificar(String email, String nuevoNombre, String nuevaContrasena) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                u.setNombre(nuevoNombre);
                u.setContrasena(nuevaContrasena);
                return;
            }
        }
    }

    public String buscarPorCorreo(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u.toString();
            }
        }
        return "No encontrado";
    }

    public void listar(){
        System.out.println("Lista de usuarios: ");
        for (Usuario u : usuarios) {
            System.out.println(u.toString());
        }
    }
}

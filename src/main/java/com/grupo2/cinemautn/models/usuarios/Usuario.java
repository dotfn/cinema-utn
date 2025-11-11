package com.grupo2.cinemautn.models.usuarios;

import com.grupo2.cinemautn.models.contenido.Contenido;

import java.util.Objects;

public class Usuario {
    //atributos
    private static int contador = 0;
    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private boolean estado;
    private Rol rol;
    private ListaFavoritos listaFavoritos;

    //constructor
    public Usuario(String nombre, String email, String contrasena, Rol rol) {
        contador ++;
        this.idUsuario = contador;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.estado = true; //activo por defecto
        this.rol = rol;
        this.listaFavoritos = new ListaFavoritos();
    }

    public Usuario() {
        contador ++;
        this.idUsuario = contador;
        this.estado = true; //activo por defecto
        listaFavoritos = new ListaFavoritos();
    }

    //getters y settersp

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    //otros métodos

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", estado=" + estado +
                ", rol=" + rol +
                ", listaFavoritos=" + listaFavoritos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    public void agregarAFavoritos(Contenido contenido) {
        this.listaFavoritos.agregarFavorito(contenido);
    }

    public void eliminarDeFavoritos(Contenido contenido) {
        this.listaFavoritos.eliminarFavorito(contenido);
    }

    public void verFavoritos() {
        System.out.println(this.listaFavoritos.getFavoritos());
    }

    public void calificarContenido(int idContenido, int estrellas) {

    }
}

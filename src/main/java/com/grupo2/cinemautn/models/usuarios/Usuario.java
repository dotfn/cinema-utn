package com.grupo2.cinemautn.models.usuarios;

import java.util.Objects;

public class Usuario {
    //atributos
    private static int contador = 0; //TODO: revisar inicialización desde JSON
    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private boolean estado;
    private Rol rol;

    //constructor
    public Usuario(String nombre, String email, String contrasena, Rol rol) {
        contador ++;
        this.idUsuario = contador;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.estado = true; //activo por defecto
        this.rol = rol;
    }

    public Usuario() {
        contador ++;
        this.idUsuario = contador;
        this.estado = true; //activo por defecto
    }

    //getters y settersp
    public int getIdUsuario() {return idUsuario;}

    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario && estado == usuario.estado && Objects.equals(nombre, usuario.nombre) && Objects.equals(email, usuario.email) && Objects.equals(contrasena, usuario.contrasena) && rol == usuario.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, email, contrasena, estado, rol);
    }
}

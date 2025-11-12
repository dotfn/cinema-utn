package com.grupo2.cinemautn.models.usuarios;
import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.models.contenido.Genero;
import com.grupo2.cinemautn.models.contenido.Pelicula;
import com.grupo2.cinemautn.models.contenido.Serie;
import com.grupo2.cinemautn.service.ContenidoService;
import com.grupo2.cinemautn.service.UsuarioService;


public class Administrador extends Usuario{
    UsuarioService usuarioService = new UsuarioService();
    ContenidoService contenidoService = new ContenidoService();

    //constructor
    public Administrador(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena, Rol.ADMINISTRADOR);
    }

    public Administrador(){
        setRol(Rol.ADMINISTRADOR);
    }

    //metodos

    //gestion de contenido
    public void crearContenido(String titulo, Genero genero, int anio, String director, int temporadas, int episodios) {
        Contenido contenido = new Serie(titulo, genero, anio, director, temporadas, episodios);
        contenidoService.alta(contenido);
    }

    public void crearContenido(String titulo, Genero genero, int anio, String director, double duracion) {
        Contenido contenido = new Pelicula(titulo, genero, anio, director, duracion);
        contenidoService.alta(contenido);
    }

    public void eliminarContenido(Contenido contenido) {
        contenidoService.baja(contenido.getId());
    }

    public void modificarContenido(String titulo, Genero genero, int anio, String director, int temporadas, int episodios) {
        Contenido contenido = new Serie(titulo, genero, anio, director, temporadas, episodios);
        contenidoService.modificacion(contenido);
    }

    public void modificarContenido(String titulo, Genero genero, int anio, String director, double duracion) {
        Contenido contenido = new Pelicula(titulo, genero, anio, director, duracion);
        contenidoService.modificacion(contenido);
    }

    public Contenido consultarContenido(Contenido contenido) {
        return contenidoService.consulta(contenido.getId());
    }

    public void listarContenidos() {
        contenidoService.listar();
    }

    //gestion de usuarios
    public void crearUsuario(String nombre, String email, String contrasena, Rol rol) {
        Usuario u = new Usuario(nombre, email, contrasena, rol);
        usuarioService.alta(u);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioService.baja(usuario.getIdUsuario());
    }

    public void modificarUsuario(String nuevoEmail, String nuevoNombre, String nuevaContrasena) {
        Usuario u = new Usuario(nuevoEmail, nuevoNombre, nuevaContrasena, Rol.PREMIUM);
        usuarioService.modificacion(u);
    }

    public Usuario consultarUsuario(Usuario usuario) {
        return usuarioService.consulta(usuario.getIdUsuario());
    }

    public void listarUsuarios() {
        usuarioService.listar();
    }
}

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

    // overload con imagenPortada para Serie
    public void crearContenido(String titulo, Genero genero, int anio, String director, int temporadas, int episodios, String imagenPortada) {
        Contenido contenido = new Serie(titulo, genero, anio, director, temporadas, episodios, imagenPortada);
        contenidoService.alta(contenido);
    }



    // overload con imagenPortada para Pelicula
    public void crearContenido(String titulo, Genero genero, int anio, String director, double duracion, String imagenPortada) {
        Contenido contenido = new Pelicula(titulo, genero, anio, director, duracion, imagenPortada);
        contenidoService.alta(contenido);
    }

    public void eliminarContenido(Contenido contenido) {
        contenidoService.baja(contenido.getId());
    }


    // overload modificacion para Serie con imagen
    public void modificarContenido(String titulo, Genero genero, int anio, String director, int temporadas, int episodios, String imagenPortada) {
        Contenido contenido = new Serie(titulo, genero, anio, director, temporadas, episodios, imagenPortada);
        contenidoService.modificacion(contenido);
    }


    // overload modificacion para Pelicula con imagen
    public void modificarContenido(String titulo, Genero genero, int anio, String director, double duracion, String imagenPortada) {
        Contenido contenido = new Pelicula(titulo, genero, anio, director, duracion, imagenPortada);
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

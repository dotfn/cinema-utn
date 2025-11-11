package com.grupo2.cinemautn.models.usuarios;
import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.models.contenido.Genero;
import com.grupo2.cinemautn.service.ContenidoService;
import com.grupo2.cinemautn.service.UsuarioService;


public class Administrador extends Usuario{
    //constructor
    public Administrador(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena, Rol.ADMINISTRADOR);
    }

    public Administrador(){
        setRol(Rol.ADMINISTRADOR);
    }

    //metodos
    //gestion de contenido
    public void crearContenido(ContenidoService contenidoService, Contenido contenido) {
        contenidoService.crear(contenido);
    }

    public void leerContenido(ContenidoService contenidoService, int id) {
            Contenido c = contenidoService.leer(id);
    }

    public void actualizarContenido(ContenidoService contenidoService, Contenido contenidoActualizado) {
        contenidoService.actualizar(contenidoActualizado);
    }

    public void eliminarContenido(ContenidoService contenidoService, int id) {
        contenidoService.eliminar(id);
    }

    public void listarContenidos(ContenidoService contenidoService) {
        System.out.println("Lista de contenidos:");
        for (Contenido c : contenidoService.listar()) {
            System.out.println(c.toString());
        }
    }

    public void buscarContenidoPorGenero(ContenidoService contenidoService, Genero genero) {
        System.out.println("Contenidos del género " + genero + ":");
        for (Contenido c : contenidoService.buscarPorGenero(genero)) {
            System.out.println(c);
        }
    }

    //gestion de usuarios
    public void crearUsuario(UsuarioService usuarioService, String nombre, String email, String contrasena, Rol rol) {
        usuarioService.crear(nombre, email, contrasena, rol);
    }

    public void eliminarUsuario(UsuarioService usuarioService, String email) {
        usuarioService.eliminar(email);
    }

    public void modificarUsuario(UsuarioService usuarioService, String email, String nuevoNombre, String nuevaContrasena) {
        usuarioService.modificar(email, nuevoNombre, nuevaContrasena);
    }

    public void listarUsuarios(UsuarioService usuarioService) {
        usuarioService.listar();
    }

    public void buscarUsuario(UsuarioService usuarioService, String email) {
        System.out.println(usuarioService.buscarPorCorreo(email));
    }
}

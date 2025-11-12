// java
package com.grupo2.cinemautn.models.usuarios;
import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.models.contenido.Genero;
import com.grupo2.cinemautn.service.ContenidoService;
import com.grupo2.cinemautn.service.UsuarioService;

public class Administrador extends Usuario{
    public Administrador(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena, Rol.ADMINISTRADOR);
    }

    public Administrador(){
        setRol(Rol.ADMINISTRADOR);
    }

    // gestión de contenido
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

    // gestión de usuarios
    public void crearUsuario(UsuarioService usuarioService, String nombre, String email, String contrasena, Rol rol) {
        Usuario nuevo = new Usuario(nombre, email, contrasena, rol);
        usuarioService.crear(nuevo);
    }

    public void eliminarUsuario(UsuarioService usuarioService, String email) {
        Usuario u = usuarioService.buscarPorCorreo(email);
        if (u != null) {
            usuarioService.eliminar(u.getIdUsuario());
        }
    }

    /**
     * Modifica el usuario identificado por 'emailActual'. Si necesita usar otro criterio,
     * cambiar el primer parámetro a la clave apropiada.
     */
    public void modificarUsuario(UsuarioService usuarioService, String emailActual, String nuevoEmail, String nuevoNombre, String nuevaContrasena) {
        Usuario u = usuarioService.buscarPorCorreo(emailActual);
        if (u != null) {
            u.setEmail(nuevoEmail);
            u.setNombre(nuevoNombre);
            u.setContrasena(nuevaContrasena);
            usuarioService.actualizar(u);
        }
    }

    public void listarUsuarios(UsuarioService usuarioService) {
        for (Usuario u : usuarioService.listar()) {
            System.out.println(u);
        }
    }

    public void buscarUsuario(UsuarioService usuarioService, String email) {
        System.out.println(usuarioService.buscarPorCorreo(email));
    }
}
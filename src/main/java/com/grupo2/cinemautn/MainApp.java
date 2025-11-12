package com.grupo2.cinemautn;

import com.grupo2.cinemautn.app.Cinema;
import com.grupo2.cinemautn.models.usuarios.Rol;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import javafx.application.Application;

import static com.grupo2.cinemautn.service.UsuarioActivoService.usuarioService;

public class MainApp {
    public static void main(String[] args) {
        //Usuario nuevo = new Usuario("Juan Perez", "soyJuna@admin.com", "elAdmin", Rol.ADMINISTRADOR);
        //usuarioService.crear(nuevo);
        Application.launch(Cinema.class, args);
    }
}

package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.service.AuthService;
import com.grupo2.cinemautn.service.UsuarioActivoService;
import com.grupo2.cinemautn.service.UsuarioService;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Hyperlink registerLink;
    @FXML private Label statusLabel;

    // servicios compartidos
    private static final UsuarioService usuarioService = UsuarioActivoService.usuarioService;
    private static final AuthService authService = UsuarioActivoService.authService;

    @FXML
    public void onLogin(javafx.event.ActionEvent event) throws IOException {

        String email = emailField.getText();
        String pass = passwordField.getText();

        if (email == null || email.isBlank() || pass == null || pass.isBlank()) {
            statusLabel.setText("Por favor ingrese correo y contraseña.");
            return;
        }

        Usuario u = authService.iniciarSesion(email.trim(), pass);
        if (u == null) {
            statusLabel.setText("Credenciales inválidas.");
            return;
        }
        // Navegar al dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/dashboard.fxml"));
        Scene mainScene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(mainScene);
        stage.setTitle("Principal");
        stage.show();
    }

    @FXML
    public void onRegister(ActionEvent event) throws IOException {
        // Abrir pantalla de registro
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/register.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registro - Cinema UTN");
        stage.show();
    }

}

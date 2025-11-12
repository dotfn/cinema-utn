package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.service.AuthService;
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

import java.io.IOException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Hyperlink registerLink;
    @FXML private Label statusLabel;

    private final AuthService authService = new AuthService();

    @FXML
    public void onLogin(javafx.event.ActionEvent event) throws IOException {

        String email = emailField.getText();
        String pass = passwordField.getText();

        // Autenticar
        Usuario usuario = authService.authenticate(email, pass);
        if (usuario == null) {
            statusLabel.setText("Credenciales inválidas o usuario inactivo.");
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
    public void onRegister(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/register.fxml"));
        Scene registerScene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(registerScene);
        stage.setTitle("Registro");
        stage.show();
    }

}

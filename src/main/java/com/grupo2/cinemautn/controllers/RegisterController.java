package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.models.usuarios.Rol;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.service.UsuarioActivoService;
import com.grupo2.cinemautn.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class RegisterController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private CheckBox chkTerms;
    @FXML private Button btnCrear;
    @FXML private Button btnCancelar;
    @FXML private Button btnBack;
    @FXML private Label statusLabel;

    private static final UsuarioService usuarioService =  UsuarioActivoService.usuarioService;

    @FXML
    private void initialize() {
        System.out.println("[DEBUG] Inicializando RegisterController");
        statusLabel.setText("");
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        // Volver a login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/login.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login - Cinema UTN");
        stage.show();
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        // Limpiar formulario
        txtNombre.clear();
        txtCorreo.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        chkTerms.setSelected(false);
        statusLabel.setText("");
    }

    @FXML
    private void onCrearUsuario(ActionEvent event) throws IOException {
        // Validaciones mínimas
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String pass = passwordField.getText();
        String pass2 = confirmPasswordField.getText();

        if (nombre == null || nombre.isBlank() || correo == null || correo.isBlank()) {
            statusLabel.setText("Nombre y correo son obligatorios.");
            return;
        }
        if (pass == null || pass.isBlank() || !pass.equals(pass2)) {
            statusLabel.setText("Las contraseñas no coinciden o están vacías.");
            return;
        }
        if (!chkTerms.isSelected()) {
            statusLabel.setText("Debe aceptar los términos y condiciones.");
            return;
        }

        // Verificar si existe usuario con mismo correo
        if (usuarioService.buscarPorCorreo(correo) != null) {
            statusLabel.setText("Ya existe un usuario con ese correo.");
            return;
        }

        // Crear y persistir usuario
        Usuario nuevo = new Usuario(nombre, correo, pass, Rol.BASE);
        usuarioService.crear(nuevo);

        // Navegar al login después del registro
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/login.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login - Cinema UTN");
        stage.show();
    }
}

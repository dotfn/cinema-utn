package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.models.usuarios.Rol;
import com.grupo2.cinemautn.service.UsuarioService;
import com.grupo2.cinemautn.persistence.GestoraUsuariosJSON;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    @FXML private Label statusLabel;

    private UsuarioService usuarioService = new UsuarioService();
    private final GestoraUsuariosJSON gestoraUsuarios = new GestoraUsuariosJSON();
    private final String ARCHIVO_USUARIOS = "usuarios.json";

    @FXML
    public void onCrearUsuario(javafx.event.ActionEvent event) throws IOException {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String pass = passwordField.getText();
        String confirm = confirmPasswordField.getText();

        // Validaciones básicas
        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
            statusLabel.setText("Por favor complete todos los campos obligatorios.");
            return;
        }

        if (!pass.equals(confirm)) {
            statusLabel.setText("Las contraseñas no coinciden.");
            return;
        }

        if (!chkTerms.isSelected()) {
            statusLabel.setText("Debe aceptar los términos y condiciones.");
            return;
        }

        // Cargar usuarios existentes desde JSON
        ArrayList<com.grupo2.cinemautn.models.usuarios.Usuario> usuarios = gestoraUsuarios.archivoALista(ARCHIVO_USUARIOS);

        // Verificar si el correo ya existe en el archivo
        for (com.grupo2.cinemautn.models.usuarios.Usuario u : usuarios) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(correo)) {
                statusLabel.setText("El correo ya está registrado.");
                return;
            }
        }

        // Crear usuario con rol BASE por defecto y añadir a la lista
        com.grupo2.cinemautn.models.usuarios.Usuario nuevo = new com.grupo2.cinemautn.models.usuarios.Usuario(nombre, correo, pass, Rol.BASE);
        usuarios.add(nuevo);
        // Persistir
        gestoraUsuarios.listaToArchivo(usuarios, ARCHIVO_USUARIOS);

        statusLabel.setStyle("-fx-text-fill: #080;");
        statusLabel.setText("Usuario creado correctamente. Redirigiendo...");

        // Navegar a la pantalla de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/login.fxml"));
        Scene loginScene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    public void onCancelar(javafx.event.ActionEvent event) throws IOException {
        // Volver al login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/login.fxml"));
        Scene loginScene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    public void onBack(javafx.event.ActionEvent event) throws IOException {
        onCancelar(event);
    }
}

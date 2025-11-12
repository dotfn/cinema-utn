package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.service.UsuarioActivoService;
import com.grupo2.cinemautn.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class UserProfileController {

    @FXML private ImageView profileImage;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;
    @FXML private Button btnEditar;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    @FXML private Button btnBack;
    @FXML private Label statusLabel;

    private UsuarioService usuarioService = UsuarioActivoService.usuarioService;
    private Usuario usuarioActual;

    // Copias temporales para revertir
    private String tempNombre;
    private String tempCorreo;
    private String tempTelefono;
    private String tempDireccion;

    @FXML
    private void initialize() {
        System.out.println("[DEBUG] Inicializando controlador de perfil...");

        // Obtener usuario logueado
        usuarioActual = UsuarioActivoService.authService.getUsuarioActual();
        if (usuarioActual == null) {
            // No hay usuario logueado: volver a login
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/login.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) txtNombre.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login - Cinema UTN");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        setEditable(false);
        loadUserData();
        updateStatus("Perfil cargado correctamente.");
    }

    private void loadUserData() {
        if (usuarioActual == null) return;
        txtNombre.setText(usuarioActual.getNombre());
        txtCorreo.setText(usuarioActual.getEmail());
        // telefono y direccion no forman parte actualmente de Usuario; si los agregas, mapearlos aquí
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

    @FXML
    private void onEditar() {
        tempNombre = txtNombre.getText();
        tempCorreo = txtCorreo.getText();
        tempTelefono = txtTelefono.getText();
        tempDireccion = txtDireccion.getText();

        setEditable(true);
        updateStatus("Modo edición activado.");
    }

    @FXML
    private void onGuardar() {
        if (usuarioActual == null) return;

        usuarioActual.setNombre(txtNombre.getText());
        usuarioActual.setEmail(txtCorreo.getText());
        // telefono/direccion: no persistidos aquí salvo que extiendas Usuario

        // persistir cambios
        usuarioService.actualizar(usuarioActual);

        setEditable(false);
        updateStatus("Cambios guardados correctamente.");
    }

    @FXML
    private void onCancelar() {
        txtNombre.setText(tempNombre);
        txtCorreo.setText(tempCorreo);
        txtTelefono.setText(tempTelefono);
        txtDireccion.setText(tempDireccion);

        setEditable(false);
        updateStatus("Cambios cancelados.");
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        System.out.println("[DEBUG] Volviendo al dashboard...");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/dashboard.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Dashboard - Cinema UTN");
        stage.show();

        updateStatus("Regresando al Dashboard...");
    }

    private void setEditable(boolean editable) {
        txtNombre.setEditable(editable);
        txtCorreo.setEditable(editable);
        txtTelefono.setEditable(editable);
        txtDireccion.setEditable(editable);

        btnEditar.setDisable(editable);
        btnGuardar.setDisable(!editable);
        btnCancelar.setDisable(!editable);
    }

    private void updateStatus(String text) {
        if (statusLabel != null) {
            statusLabel.setText(text);
        }
    }
}

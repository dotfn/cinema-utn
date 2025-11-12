package com.grupo2.cinemautn.controllers;

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

    // Datos simulados (mock)
    private String nombre = "Juan Pérez";
    private String correo = "juan.perez@correo.com";


    // Copias temporales para revertir
    private String tempNombre;
    private String tempCorreo;


    @FXML
    private void initialize() {
        System.out.println("[DEBUG] Inicializando controlador de perfil...");
        setEditable(false);
        loadMockUserData();
        updateStatus("Perfil cargado correctamente.");
    }

    private void loadMockUserData() {
        txtNombre.setText(nombre);
        txtCorreo.setText(correo);

    }

    @FXML
    private void onEditar() {
        tempNombre = txtNombre.getText();
        tempCorreo = txtCorreo.getText();

        setEditable(true);
        updateStatus("Modo edición activado.");
    }

    @FXML
    private void onGuardar() {
        nombre = txtNombre.getText();
        correo = txtCorreo.getText();

        System.out.println("[MOCK] Datos actualizados:");
        System.out.println("  - Nombre: " + nombre);
        System.out.println("  - Correo: " + correo);

        setEditable(false);
        updateStatus("Cambios guardados correctamente.");
    }

    @FXML
    private void onCancelar() {
        txtNombre.setText(tempNombre);
        txtCorreo.setText(tempCorreo);

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

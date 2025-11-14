package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.models.usuarios.Rol;
import com.grupo2.cinemautn.service.SesionActivaService;
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
    @FXML private Button btnEditar;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    @FXML private Button btnBack;
    @FXML private Label statusLabel;
    @FXML private TextField txtContrasena;
    @FXML private ComboBox<Rol> cmbRol; // nuevo

    // Datos simulados (mock)
    private String nombre = "Juan Pérez";
    private String correo = "juan.perez@correo.com";
    private String contrasena = "********";

    // Copias temporales para revertir
    private String tempNombre;
    private String tempCorreo;
    private String tempContrasena;
    private Rol tempRol;
    // Usuario actual en la sesión (puede venir de setUser o de SesionActivaService)
    private Usuario usuarioActual;

    // Servicio para persistencia de usuarios
    private UsuarioService usuarioService = new UsuarioService();


    @FXML
    private void initialize() {
        System.out.println("[DEBUG] Inicializando controlador de perfil...");
        setEditable(false);
        // Inicializar ComboBox de roles
        if (cmbRol != null) {
            cmbRol.getItems().clear();
            cmbRol.getItems().addAll(Rol.BASE, Rol.PREMIUM);
            cmbRol.setValue(Rol.BASE);
        }
        // Intentar cargar usuario desde SesionActivaService
        if (this.usuarioActual == null) {
            this.usuarioActual = SesionActivaService.getInstance().getUsuario();
        }
        if (this.usuarioActual != null) {
            setUser(this.usuarioActual);
        } else {
            loadMockUserData();
        }
        updateStatus("Perfil cargado correctamente.");
    }

    private void loadMockUserData() {
        txtNombre.setText(nombre);
        txtCorreo.setText(correo);
        txtContrasena.setText("********");
        if (cmbRol != null) cmbRol.setValue(Rol.BASE);

    }

    @FXML
    private void onEditar() {
        tempNombre = txtNombre.getText();
        tempCorreo = txtCorreo.getText();
        tempContrasena = txtContrasena.getText();
        tempRol = cmbRol != null ? cmbRol.getValue() : null;
        setEditable(true);
        updateStatus("Modo edición activado.");
    }

    @FXML
    private void onGuardar() {
        nombre = txtNombre.getText();
        correo = txtCorreo.getText();
        contrasena = txtContrasena.getText();
        Rol seleccionado = cmbRol != null ? cmbRol.getValue() : null;

        // Si hay un usuario en sesión, actualizarlo también
        if (usuarioActual != null) {
            usuarioActual.setNombre(nombre);
            // permitir cambiar email si el servicio lo valida
            if (correo != null && !correo.isBlank()) {
                usuarioActual.setEmail(correo);
            }
            if (contrasena != null && !contrasena.isBlank()) {
                usuarioActual.setContrasena(contrasena);
            }
            if (seleccionado != null) {
                usuarioActual.setRol(seleccionado);
            }

            // Persistir cambios usando UsuarioService (ABMCL.modificacion)
            usuarioService.modificacion(usuarioActual);

            // Actualizar la sesión activa
            SesionActivaService.getInstance().setUsuario(usuarioActual);
        }
        setEditable(false);
        updateStatus("Cambios guardados correctamente.");
    }

    @FXML
    private void onCancelar() {
        txtNombre.setText(tempNombre);
        txtCorreo.setText(tempCorreo);
        txtContrasena.setText(tempContrasena);
        if (cmbRol != null) cmbRol.setValue(tempRol);

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
        txtContrasena.setEditable(editable);
        if (cmbRol != null) cmbRol.setDisable(!editable);

        btnEditar.setDisable(editable);
        btnGuardar.setDisable(!editable);
        btnCancelar.setDisable(!editable);
    }

    private void updateStatus(String text) {
        if (statusLabel != null) {
            statusLabel.setText(text);
        }
    }

    /**
     * Permite que el controlador reciba el Usuario que está logueado.
     * Debe llamarse justo después de `loader.load()` desde quien navega.
     */
    public void setUser(Usuario usuario) {
        this.usuarioActual = usuario;
        if (usuarioActual != null) {
            txtNombre.setText(usuarioActual.getNombre() != null ? usuarioActual.getNombre() : "");
            txtCorreo.setText(usuarioActual.getEmail() != null ? usuarioActual.getEmail() : "");
            txtContrasena.setText(usuarioActual.getContrasena() != null ? usuarioActual.getContrasena() : "");
            if (cmbRol != null && usuarioActual.getRol() != null) cmbRol.setValue(usuarioActual.getRol());
        }
    }
}

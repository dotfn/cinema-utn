package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.service.SesionActivaService;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    // --- FXML bindings (según dashboard.fxml) ---
    @FXML private TextField searchField;

    @FXML private ImageView imgPoster;
    @FXML private Label lblTitulo;
    @FXML private Label lblDirector;
    @FXML private Label lblRating;
    @FXML private Label lblDuracion;
    @FXML private TextArea txtDescripcionHeader;

    @FXML private TableView<Contenido> tableView;
    @FXML private TableColumn<Contenido, String> colNombre;
    @FXML private TableColumn<Contenido, String> colDescription;
    @FXML private TableColumn<Contenido, String> colDirector;

    @FXML private Label statusLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void filterOnSearch() {
    }

    @FXML
    public void onVerDetalles() {
    }

    @FXML
    public void onReproducirTrailer() {
    }

    @FXML
    public void onEditar() {
    }

    @FXML
    public void onEliminar() {
    }

    @FXML
    public void onAgregar() {
    }

    @FXML
    public void onUserProfile(javafx.event.ActionEvent event) throws IOException {

        // Navegar al userProfile
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/userProfile.fxml"));
        // Cargar fxml y obtener controller
        javafx.scene.Parent root = loader.load();
        Object ctrl = loader.getController();
        Usuario logged = SesionActivaService.getInstance().getUsuario();
        if (ctrl instanceof com.grupo2.cinemautn.controllers.UserProfileController) {
            ((com.grupo2.cinemautn.controllers.UserProfileController) ctrl).setUser(logged);
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Perfil de Usuario");
        stage.show();

    }

    @FXML
    public void onLogout(javafx.event.ActionEvent event) throws IOException {
        // Limpiar la sesión activa
        SesionActivaService.getInstance().clear();

        // Volver a la pantalla de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/login.fxml"));
        javafx.scene.Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}

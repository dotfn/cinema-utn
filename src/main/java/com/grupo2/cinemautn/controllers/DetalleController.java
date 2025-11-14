package com.grupo2.cinemautn.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;

public class DetalleController {

    // Campos enlazados desde detalle.fxml (solo vista, sin funcionalidad)
    @FXML
    private Label lblTitle;

    @FXML
    private ImageView imgPoster;

    @FXML
    private Label lblGenero;

    @FXML
    private Label lblAnio;

    @FXML
    private Label lblDirector;

    @FXML
    private Rating ratingPromedio;

    @FXML
    private VBox reviewsContainer;

    // Método initialize vacío (sin lógica por ahora)
    @FXML
    public void initialize() {
        // Intencionalmente vacío: la vista es estática por ahora
    }
    @FXML
    public void onCancelar(javafx.event.ActionEvent event) throws IOException {
        // Navegar al dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/grupo2/cinemautn/fxml/dashboard.fxml"));
        Scene mainScene = new Scene(loader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(mainScene);
        stage.setTitle("Principal");
        stage.show();
    }

    @FXML
    public void onBack(javafx.event.ActionEvent event) throws IOException {
        onCancelar(event);
    }
}

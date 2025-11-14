package com.grupo2.cinemautn.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

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
}

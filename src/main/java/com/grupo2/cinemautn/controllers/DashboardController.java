package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.service.ContenidoService;
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
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Nuevos imports para instanciar Pelicula y Serie
import com.grupo2.cinemautn.models.contenido.Pelicula;
import com.grupo2.cinemautn.models.contenido.Serie;
import com.grupo2.cinemautn.models.contenido.Genero;

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
        // Inicializar la tabla con los contenidos
        ContenidoService contenidoService = new ContenidoService();
        ObservableList<Contenido> items = tableView.getItems();
        items.setAll(contenidoService.listar());

        // Hardcode: crear una película y una serie para mostrar en el dashboard
        Pelicula peliculaHardcode = new Pelicula(
                "El Gran Viaje",
                Genero.ACCION,
                2023,
                "Lucía Martínez",
                2.15,
                "poster_el_gran_viaje.jpg"
        );

        Serie serieHardcode = new Serie(
                "Misterios del Cosmos",
                Genero.CIENCIA_FICCION,
                2021,
                "Ada Curie",
                3,
                24,
                "poster_misterios_cosmos.jpg"
        );

        // Añadir los hardcodeados al listado que alimenta la TableView (no persiste en archivo)
        items.addAll(peliculaHardcode, serieHardcode);

        // Configurar las columnas de la tabla
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitulo()));
        colDescription.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenero().toString()));
        colDirector.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDirector()));



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

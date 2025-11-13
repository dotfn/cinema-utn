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

// Import adicionales para bindings y carga de imagenes
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {

    // --- FXML bindings (según dashboard.fxml) ---
    @FXML private TextField searchField;

    @FXML private ImageView imgPoster;
    @FXML private Label lblTitulo;
    @FXML private Label lblDirector;
    @FXML private Label lblRating;
    @FXML private Label lblDuracion;

    @FXML private TableView<Contenido> tableView;
    @FXML private TableColumn<Contenido, String> colNombre;
    @FXML private TableColumn<Contenido, String> colDuracion;
    @FXML private TableColumn<Contenido, String> colGenero;
    @FXML private TableColumn<Contenido, String> colDirector;

    @FXML private Label statusLabel;

    // Servicio para acceder a los contenidos
    private ContenidoService contenidoService = new ContenidoService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar columnas
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue() != null ? cell.getValue().getTitulo() : ""));
        colDirector.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue() != null ? cell.getValue().getDirector() : ""));

        // Columna Duración: muestra duración para Película o temporadas/episodios para Serie
        colDuracion.setCellValueFactory(cell -> {
            Contenido c = cell.getValue();
            if (c == null) return new SimpleStringProperty("");
            if (c instanceof Pelicula) {
                Pelicula p = (Pelicula) c;
                return new SimpleStringProperty(p.getDuracion() + " h");
            } else if (c instanceof Serie) {
                Serie s = (Serie) c;
                return new SimpleStringProperty(s.getTemporadas() + " T, " + s.getEpisodios() + " ep");
            }
            return new SimpleStringProperty("-");
        });

        // Columna Género
        colGenero.setCellValueFactory(cell -> {
            Contenido c = cell.getValue();
            if (c == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(c.getGenero() != null ? c.getGenero().name() : "");
        });

        // Cargar datos desde el servicio
        List<Contenido> lista = contenidoService.listar();
        tableView.setItems(FXCollections.observableArrayList(lista));

        // Listener para selección
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            showDetails(newSel);
        });

        // Seleccionar el primer elemento si existe
        if (!lista.isEmpty()) {
            tableView.getSelectionModel().select(0);
            showDetails(lista.get(0));
        }

        statusLabel.setText("Cargados: " + lista.size());
    }

    @FXML
    public void filterOnSearch() {
        String q = searchField.getText();
        List<Contenido> all = contenidoService.listar();
        if (q == null || q.trim().isEmpty()) {
            tableView.setItems(FXCollections.observableArrayList(all));
            statusLabel.setText("Cargados: " + all.size());
            return;
        }
        String ql = q.trim().toLowerCase();
        List<Contenido> filtered = all.stream()
                .filter(c -> c.getTitulo() != null && c.getTitulo().toLowerCase().contains(ql))
                .collect(Collectors.toList());
        tableView.setItems(FXCollections.observableArrayList(filtered));
        statusLabel.setText("Mostrando: " + filtered.size());
        if (!filtered.isEmpty()) tableView.getSelectionModel().select(0);
    }

    @FXML
    public void onVerDetalles() {
        Contenido sel = tableView.getSelectionModel().getSelectedItem();
        if (sel != null) {
            // actualmente no hay una ventana específica, simplemente actualizar estado
            statusLabel.setText("Ver detalles: " + sel.getTitulo());
        }
    }

    @FXML
    public void onReproducirTrailer() {
        Contenido sel = tableView.getSelectionModel().getSelectedItem();
        if (sel != null) {
            statusLabel.setText("Reproducir trailer: " + sel.getTitulo());
        }
    }

    @FXML
    public void onEditar() {
        Contenido sel = tableView.getSelectionModel().getSelectedItem();
        if (sel != null) statusLabel.setText("Editar: " + sel.getTitulo());
    }

    @FXML
    public void onEliminar() {
        Contenido sel = tableView.getSelectionModel().getSelectedItem();
        if (sel != null) {
            try {
                contenidoService.baja(sel.getId());
                tableView.getItems().remove(sel);
                statusLabel.setText("Eliminado: " + sel.getTitulo());
            } catch (Exception e) {
                statusLabel.setText("Error eliminando: " + e.getMessage());
            }
        }
    }

    @FXML
    public void onAgregar() {
        statusLabel.setText("Agregar nuevo contenido (no implementado)");
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

    // Muestra la información del contenido seleccionado en el header
    private void showDetails(Contenido c) {
        if (c == null) {
            lblTitulo.setText("");
            lblDirector.setText("");
            lblRating.setText("-");
            lblDuracion.setText("-");
            imgPoster.setImage(null);
            return;
        }

        lblTitulo.setText(c.getTitulo() != null ? c.getTitulo() : "");
        lblDirector.setText(c.getDirector() != null ? c.getDirector() : "");
        double prom = c.promedioResenas();
        lblRating.setText(prom > 0 ? String.format("%.1f", prom) : "-");

        if (c instanceof Pelicula) {
            Pelicula p = (Pelicula) c;
            lblDuracion.setText(p.getDuracion() + " h");
        } else if (c instanceof Serie) {
            Serie s = (Serie) c;
            lblDuracion.setText(s.getTemporadas() + " T, " + s.getEpisodios() + " ep");
        } else {
            lblDuracion.setText("-");
        }
    }
}

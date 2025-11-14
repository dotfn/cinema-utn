package com.grupo2.cinemautn.controllers;

import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.models.resena.Resena;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.service.ContenidoService;
import com.grupo2.cinemautn.service.SesionActivaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DetalleController {

    // Campos enlazados desde detalle.fxml (solo vista, con entrada de reseñas)
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

    @FXML
    private Rating inputRating;

    @FXML
    private TextArea txtComentario;

    // Contenido actual mostrado
    private Contenido contenido;

    private final ContenidoService contenidoService = new ContenidoService();

    // Método initialize
    @FXML
    public void initialize() {
        // nada por ahora
    }

    // Método público para que otros controladores pasen el contenido a mostrar
    public void setContenido(Contenido c) {
        this.contenido = c;
        populateView();
    }

    // Poblado de la vista a partir del contenido
    private void populateView() {
        if (contenido == null) return;

        if (lblTitle != null) lblTitle.setText(contenido.getTitulo() != null ? contenido.getTitulo() : "");
        if (lblGenero != null) lblGenero.setText(contenido.getGenero() != null ? contenido.getGenero().name() : "");
        if (lblAnio != null) lblAnio.setText(contenido.getAnio() > 0 ? String.valueOf(contenido.getAnio()) : "");
        if (lblDirector != null) lblDirector.setText(contenido.getDirector() != null ? contenido.getDirector() : "");

        // Cargar imagen desde resources/data/img/<imagen>
        if (imgPoster != null) {
            String imgName = contenido.getImagenPortada();
            if (imgName != null && !imgName.isBlank()) {
                String path = "/data/img/" + imgName;
                try (InputStream is = getClass().getResourceAsStream(path)) {
                    if (is != null) {
                        Image img = new Image(is);
                        imgPoster.setImage(img);
                    } else {
                        imgPoster.setImage(null);
                    }
                } catch (Exception e) {
                    imgPoster.setImage(null);
                }
            } else {
                imgPoster.setImage(null);
            }
        }

        // Calificación promedio
        if (ratingPromedio != null) {
            double prom = contenido.promedioResenas();
            ratingPromedio.setPartialRating(false);
            ratingPromedio.setRating(Math.max(0, Math.min(5, prom)));
        }

        // Reseñas
        if (reviewsContainer != null) {
            reviewsContainer.getChildren().clear();
            if (contenido.getResenas() != null && !contenido.getResenas().isEmpty()) {
                for (Resena r : contenido.getResenas()) {
                    HBox h = new HBox(10);
                    VBox v = new VBox(4);
                    Label user = new Label("Usuario: " + r.getIdUsuario());
                    Rating rr = new Rating();
                    rr.setPartialRating(false);
                    rr.setMax(5);
                    rr.setRating(r.getEstrellas());
                    Text comentario = new Text(r.toString());
                    comentario.wrappingWidthProperty().bind(reviewsContainer.widthProperty().subtract(60));

                    user.setStyle("-fx-font-weight:bold;");
                    v.getChildren().addAll(user, rr, comentario);
                    h.getChildren().add(v);
                    reviewsContainer.getChildren().add(h);
                }
            } else {
                Label none = new Label("No hay reseñas aún.");
                reviewsContainer.getChildren().add(none);
            }
        }
    }

    @FXML
    public void onAgregarResena() {
        if (contenido == null) return;

        int estrellas = 0;
        if (inputRating != null) {
            estrellas = (int) inputRating.getRating();
        }
        String texto = (txtComentario != null) ? txtComentario.getText() : null;

        Usuario u = SesionActivaService.getInstance().getUsuario();
        int idUsuario = (u != null) ? u.getIdUsuario() : 0;

        Resena r = new Resena(idUsuario, estrellas, texto != null ? new StringBuilder(texto) : new StringBuilder());

        if (contenido.getResenas() == null) contenido.setResenas(new ArrayList<>());
        contenido.agregarResena(r);

        // Persistir usando servicio
        try {
            contenidoService.modificacion(contenido);
        } catch (Exception e) {
            // mostrar alerta pero continuar con UI
            Alert a = new Alert(Alert.AlertType.WARNING, "No se pudo persistir la reseña: " + e.getMessage(), ButtonType.OK);
            a.showAndWait();
        }

        // limpiar inputs y refrescar vista
        if (inputRating != null) inputRating.setRating(0);
        if (txtComentario != null) txtComentario.clear();
        populateView();
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

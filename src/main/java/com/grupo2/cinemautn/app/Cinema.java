package com.grupo2.cinemautn.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Cinema extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Cinema.class.getResource("/com/grupo2/cinemautn/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 480);
        stage.setTitle("Login - Cinema UTN");
        stage.setScene(scene);
        stage.show();
    }
}

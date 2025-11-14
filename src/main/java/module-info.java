module com.grupo2.cinemautn {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.json;
    requires javafx.graphics;

    opens com.grupo2.cinemautn to javafx.fxml;
    exports com.grupo2.cinemautn;
    exports com.grupo2.cinemautn.app;
    opens com.grupo2.cinemautn.app to javafx.fxml;
    exports com.grupo2.cinemautn.controllers;
    opens com.grupo2.cinemautn.controllers to javafx.fxml;

    // Exportar paquetes de modelos concretos
    exports com.grupo2.cinemautn.models.contenido;
    exports com.grupo2.cinemautn.models.resena;
    exports com.grupo2.cinemautn.models.usuarios;
}
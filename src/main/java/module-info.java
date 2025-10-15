module com.grupo2.cinemautn {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.grupo2.cinemautn to javafx.fxml;
    exports com.grupo2.cinemautn;
}
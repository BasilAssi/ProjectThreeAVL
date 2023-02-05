module com.example.projectthreeavl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.projectthreeavl to javafx.fxml;
    exports com.example.projectthreeavl;
}
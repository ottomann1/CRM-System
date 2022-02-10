module com.example.crmsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.desktop;


    opens com.example.crmsystem to javafx.fxml;
    opens Backend.BusinessObjects to javafx.base;
    exports com.example.crmsystem;
}
module com.example.tranhue_flowersproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tranhue_flowersproject to javafx.fxml;
    exports com.example.tranhue_flowersproject;
}
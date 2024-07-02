module crud.crud_java01 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens crud.crud_java01 to javafx.fxml;
    exports crud.crud_java01;
}
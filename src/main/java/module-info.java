module com.example.oopmain {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.apache.jena.arq;
    requires org.apache.jena.core;

    opens com.example.oopmain to javafx.fxml;
    opens com.example.oopmain.controller to javafx.fxml;
    exports com.example.oopmain;
}
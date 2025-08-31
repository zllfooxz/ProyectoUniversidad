module org.example.proyectouniversidad {
    requires eu.hansolo.tilesfx;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;

    opens org.example.proyectouniversidad to javafx.fxml;
    exports org.example.proyectouniversidad;
    exports org.example.proyectouniversidad.Controladores;
    opens org.example.proyectouniversidad.Controladores to javafx.fxml;
}
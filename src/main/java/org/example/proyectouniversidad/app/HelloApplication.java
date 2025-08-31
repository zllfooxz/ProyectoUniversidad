package org.example.proyectouniversidad.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/proyectouniversidad/CapturaProductoVista.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 620);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

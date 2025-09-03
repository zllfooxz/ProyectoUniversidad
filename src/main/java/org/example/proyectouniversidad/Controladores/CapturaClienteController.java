package org.example.proyectouniversidad.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectouniversidad.domain.Cliente;

import java.io.IOException;

public class CapturaClienteController {

    @FXML
    private TextField CORREO;

    @FXML
    private TextField DIRECCION;

    @FXML
    private TextField ID;

    @FXML
    private TextField NOMBRE;

    @FXML
    private void onGuardar(ActionEvent event) {

        String nombre = NOMBRE.getText();
        String correo = CORREO.getText();
        String direccion = DIRECCION.getText();
        String id = ID.getText();

        if(nombre.isEmpty() || correo.isEmpty() || direccion.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        }

        Cliente cliente = new Cliente(id, nombre,direccion,correo);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


    }

    @FXML
    private void onVolverMenu(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyectouniversidad/MenuVista.fxml"));
            Parent root = loader.load();
            Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 750, 620));
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

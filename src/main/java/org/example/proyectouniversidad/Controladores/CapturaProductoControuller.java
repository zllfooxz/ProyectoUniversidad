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
import org.example.proyectouniversidad.app.HelloApplication;

import javax.swing.*;
import java.io.IOException;


public class CapturaProductoControuller {
    @FXML
    private TextField Cantidad;

    @FXML
    private TextField ID;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Precio;

    @FXML
    void Cancelar(ActionEvent event) {

        ID.clear();
        Cantidad.clear();
        Nombre.clear();
        Precio.clear();
    }


    @FXML
    private void GuardarProducto(ActionEvent event) {
        try {
            String id = ID.getText().trim();
            String nombre = Nombre.getText().trim();
            String precioTexto = Precio.getText().trim();
            String cantidadTexto = Cantidad.getText().trim();

            if (id.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty() || cantidadTexto.isEmpty()) {
                mostrarAlerta("Error", "Todos los campos son obligatorios.", Alert.AlertType.ERROR);
                return;
            }
            double precio = Double.parseDouble(precioTexto);
            int cantidad = Integer.parseInt(cantidadTexto);
            if (precio < 0 || cantidad < 0) {
                mostrarAlerta("Error", "Precio y cantidad no pueden ser negativos.", Alert.AlertType.ERROR);
                return;
            }

            // guardar el producto
            mostrarAlerta("Éxito", "Producto guardado correctamente.", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio y cantidad deben ser numéricos.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error inesperado", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }



    @FXML
    private void VerListaProductos(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
                    ("/org/example/proyectouniversidad/ListaDeProductos.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 750, 620));
            stage.show();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

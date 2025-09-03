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
import org.example.proyectouniversidad.Persistencia.GestorArchivosProductos; // ✅ Import específico
import org.example.proyectouniversidad.domain.Producto;

import java.io.IOException;

public class CapturaProductoControuller {

    @FXML private TextField Cantidad;
    @FXML private TextField ID;
    @FXML private TextField Nombre;
    @FXML private TextField Precio;

    private GestorArchivosProductos gestorProductos;

    @FXML
    public void initialize() {
        // ✅ Inicializar automáticamente con archivos
        gestorProductos = new GestorArchivosProductos();
        System.out.println("Gestor de archivos inicializado");
    }

    @FXML
    void Cancelar(ActionEvent event) {
        ID.clear();
        Nombre.clear();
        Precio.clear();
        Cantidad.clear();
        ID.requestFocus();
    }

    @FXML
    private void GuardarProducto(ActionEvent event) {
        try {
            String id = ID.getText().trim();
            String nombre = Nombre.getText().trim();
            String precioTexto = Precio.getText().trim();
            String cantidadTexto = Cantidad.getText().trim();

            // Validaciones
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

            // ✅ Verificar si el ID ya existe
            if (gestorProductos.buscarProducto(id) != null) {
                mostrarAlerta("Error", "El ID del producto ya existe.", Alert.AlertType.ERROR);
                return;
            }

            // Crear y guardar producto
            Producto producto = new Producto(id, nombre, precio, cantidad);
            gestorProductos.agregarProducto(producto);

            mostrarAlerta("Éxito", "Producto guardado correctamente!\nTotal productos: " +
                    gestorProductos.getTotalProductos(), Alert.AlertType.INFORMATION);

            Cancelar(null);

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio y cantidad deben ser números válidos.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "Error inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/org/example/proyectouniversidad/ListaDeProductos.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 750, 620));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la lista: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
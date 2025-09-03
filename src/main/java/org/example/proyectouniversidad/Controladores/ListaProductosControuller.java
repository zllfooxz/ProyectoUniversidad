package org.example.proyectouniversidad.Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.proyectouniversidad.Persistencia.GestorArchivosProductos;
import org.example.proyectouniversidad.domain.Producto;

import java.io.IOException;

public class ListaProductosControuller {

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> columnaId;

    @FXML
    private TableColumn<Producto, String> columnaNombre;

    @FXML
    private TableColumn<Producto, Double> columnaPrecio;

    @FXML
    private TableColumn<Producto, Integer> columnaCantidad;

    private GestorArchivosProductos gestorProductos;
    @FXML
    public void initialize() {
        // Solo configuramos las columnas
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        gestorProductos = new GestorArchivosProductos();
        cargarProductosEnTabla();
    }

    private void cargarProductosEnTabla() {
        if (gestorProductos != null) {
            ObservableList<Producto> listaObservable =
                    FXCollections.observableArrayList(gestorProductos.getTodosProductos());
            tablaProductos.setItems(listaObservable);
            System.out.println("Productos cargados en tabla: " + listaObservable.size());
        }
    }

    @FXML
    private void VolverAtras(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyectouniversidad/MenuVista.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar el menú");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

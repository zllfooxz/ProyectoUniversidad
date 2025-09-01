package org.example.proyectouniversidad.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import org.example.proyectouniversidad.domain.Producto;

public class ListaProductosControuller {

    @FXML
    private TableColumn<Producto, Integer> CantidadProducto;

    @FXML
    private TableColumn<Producto, String> IDProducto;

    @FXML
    private TableColumn<Producto, String> NombreProducto;

    @FXML
    private TableColumn<Producto, Double> PrecioProducto;

    @FXML
   private void VolverAtras() {

    }

}

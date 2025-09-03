package org.example.proyectouniversidad.Controladores;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyectouniversidad.Persistencia.GestorArchivosClientes;
import org.example.proyectouniversidad.Persistencia.GestorArchivosProductos;
import org.example.proyectouniversidad.domain.Cliente;
import org.example.proyectouniversidad.domain.Producto;

public class FacturaController {

    @FXML private TextField TexFidProducto;
    @FXML private TextField idCliente;
    @FXML private Label nomCliente;
    @FXML private TableView<Producto> table;
    @FXML private Label totalPagar;
    @FXML private Label texoIDCliente;
    @FXML private Label textoNomCliente;


    // Columnas de la tabla
    @FXML private TableColumn<Producto, String> idProducto;
    @FXML private TableColumn<Producto, String> nomProducto;
    @FXML private TableColumn<Producto, Integer> cantidadComprar;
    @FXML private TableColumn<Producto, Double> precioProducto;
    @FXML private TableColumn<Producto, Double> precioTotal;


    private GestorArchivosProductos gestorProductos;
    private GestorArchivosClientes gestorClientes;
    private ObservableList<Producto> productosCarrito;
    private double total;

    @FXML
    public void initialize() {
        // ✅ Crear NUEVAS instancias para esta ventana
        gestorProductos = new GestorArchivosProductos();
        gestorClientes = new GestorArchivosClientes();
        productosCarrito = FXCollections.observableArrayList();
        total= 0.0;
        // Configurar tabla
        configurarTabla();
        table.setItems(productosCarrito);
    }

    private void configurarTabla() {
        idProducto.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioProducto.setCellValueFactory(new PropertyValueFactory<>("precio")); //
        cantidadComprar.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        // ✅ Para la columna de Precio Total (calculada)
        precioTotal.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPrecio() * cellData.getValue().getCantidad()).asObject());
    }

    @FXML
    void onBuscarClient(ActionEvent event) {
        String id = idCliente.getText().trim();
        if (id.isEmpty()) {
            mostrarAlerta("Error", "Ingrese un ID de cliente", Alert.AlertType.ERROR);
            return;
        }

        Cliente cliente = gestorClientes.buscarClientePorId(id);
        if (cliente != null) {
            // ✅ MOSTRAR la información en los labels CORRECTOS
            textoNomCliente.setText(cliente.getNombre());
            texoIDCliente.setText(cliente.getId());  // ✅ Mostrar ID en el label correcto
        } else {
            mostrarAlerta("Error", "Cliente no encontrado", Alert.AlertType.ERROR);
            textoNomCliente.setText("No encontrado");
            texoIDCliente.setText("");
        }
    }

    @FXML
    void onBuscarProducto(ActionEvent event) {
        String id = TexFidProducto.getText().trim();
        if (id.isEmpty()) {
            mostrarAlerta("Error", "Ingrese un ID de producto", Alert.AlertType.ERROR);
            return;
        }

        Producto producto = gestorProductos.buscarProducto(id);
        if (producto != null) {
            // Agregar al carrito (puedes modificar para pedir cantidad)
            productosCarrito.add(producto);
            actualizarTotal();
            TexFidProducto.clear();
        } else {
            mostrarAlerta("Error", "Producto no encontrado", Alert.AlertType.ERROR);
        }
    }

    private void actualizarTotal() {
        double total = productosCarrito.stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();
        totalPagar.setText(String.format("$%.2f", total));
    }

    @FXML
    void onGenerarFactura(ActionEvent event) {
        if (nomCliente.getText().isEmpty()) {
            mostrarAlerta("Error", "Seleccione un cliente primero", Alert.AlertType.ERROR);
            return;
        }

        if (productosCarrito.isEmpty()) {
            mostrarAlerta("Error", "Agregue productos al carrito", Alert.AlertType.ERROR);
            return;
        }

        // Aquí puedes implementar la generación de la factura
        mostrarAlerta("Éxito", "Factura generada correctamente", Alert.AlertType.INFORMATION);
        limpiarFactura();
    }

    private void limpiarFactura() {
        idCliente.clear();
        nomCliente.setText("");
        productosCarrito.clear();
        totalPagar.setText("$0.00");
        TexFidProducto.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
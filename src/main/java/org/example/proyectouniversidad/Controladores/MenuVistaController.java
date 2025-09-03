package org.example.proyectouniversidad.Controladores;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuVistaController {

    @FXML
    protected void onFacturarProducto(ActionEvent event) {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyectouniversidad/FacturaVista.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void onGuardarCliente(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
                    ("/org/example/proyectouniversidad/CapturaCliente.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error al cargar CapturaProductoVista.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
   protected void onGuardarProducto(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
                    ("/org/example/proyectouniversidad/CapturaProductoVista.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error al cargar CapturaProductoVista.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
   protected void salir(ActionEvent event) {
        Platform.exit();
    }
}

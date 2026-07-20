/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sistema_venta;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnNuevaVenta;
    @FXML
    private Button btnProducto;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnHistorial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void NuevaVenta(ActionEvent event) {
    }

    @FXML
    private void onProducto(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/sistema_venta/producto.fxml")); 
            Parent root = loader.load();
             
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
            Scene scene = new Scene(root);
        
            stage.setScene(scene);
            stage.show();
        
        } catch (IOException e) {
            System.out.println("Error al cargar la pantalla de productos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientes(ActionEvent event) {
    }

    @FXML
    private void onHistorial(ActionEvent event) {
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sistema_venta;

import com.mycompany.sistema_venta.clases.Producto;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProductoController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtStock;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnNuevo;
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colStock;
    private ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colNombre.setCellValueFactory(celda -> new javafx.beans.property.SimpleStringProperty(celda.getValue().getNombre()));
    colPrecio.setCellValueFactory(celda -> new javafx.beans.property.SimpleObjectProperty<>(celda.getValue().getPrecio()));
    colStock.setCellValueFactory(celda -> new javafx.beans.property.SimpleObjectProperty<>(celda.getValue().getStock()));
    
    // Vinculamos la lista a la tabla
    tablaProductos.setItems(listaProductos);
   
        
    }    

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    @FXML
    private void guardarProducto(ActionEvent event) {
        if (txtNombre.getText().trim().isEmpty() || 
        txtPrecio.getText().trim().isEmpty() || 
        txtStock.getText().trim().isEmpty()) {
        
        mostrarAlerta("Error", "Todos los campos son obligatorios. Por favor, completalos.", Alert.AlertType.ERROR);
        return; 
        }
    
   
        double precio = 0;
        int stock = 0;
    
    
        try {
            precio = Double.parseDouble(txtPrecio.getText().trim());
            if (precio <= 0) {
                mostrarAlerta("Error", "El precio debe ser un valor positivo.", Alert.AlertType.ERROR);
                return;
        }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio ingresado no es válido. Debe usar puntos para los decimales.", Alert.AlertType.ERROR);
            return;
        }
    
        try {
            stock = Integer.parseInt(txtStock.getText().trim());
            if (stock < 0) {
                mostrarAlerta("Error de Datos", "El stock no puede ser un número negativo.", Alert.AlertType.ERROR);
                return;
        }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "El stock debe ser un número entero válido.", Alert.AlertType.ERROR);
            return;
        }
    
    
        try {
            String nombre = txtNombre.getText().trim();
            Producto nuevo = new Producto(0, nombre, "TEMP", "", precio, stock);
       
            listaProductos.add(nuevo);

            mostrarAlerta("Éxito", "¡El producto se ha guardado correctamente! 😊", Alert.AlertType.INFORMATION);

            btnNuevo.setDisable(false);
            btnGuardar.setDisable(true);
            btnCancelar.setDisable(true);
        
            txtNombre.setDisable(true);
            txtPrecio.setDisable(true);
            txtStock.setDisable(true);
        
            txtNombre.clear();
            txtPrecio.clear();
            txtStock.clear();
        
        } catch (Exception e) {
       
            mostrarAlerta("Error Interno", "No se pudo guardar el producto: " + e.getMessage(), Alert.AlertType.ERROR);
        }
        
        
        
    }

    @FXML
    private void eliminar(ActionEvent event) {
        btnNuevo.setDisable(false);      
        btnGuardar.setDisable(true);     
        btnCancelar.setDisable(true);     
    
    
        txtNombre.setDisable(true);
        txtPrecio.setDisable(true);
        txtStock.setDisable(true);
    
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();
        
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Atención", "Por favor, seleccione un producto de la tabla para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        listaProductos.remove(seleccionado);

        mostrarAlerta("Éxito", "El producto ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);
    
    }

    @FXML
    private void nuevoProducto(ActionEvent event) {
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);
        btnNuevo.setDisable(true);
    
        txtNombre.setDisable(false);
        txtPrecio.setDisable(false);
        txtStock.setDisable(false);
    
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();
    
    
        txtNombre.requestFocus();
    }
    
    
    @FXML
private void seleccionarBorrarProducto() {
    Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();

    if (productoSeleccionado != null) {
       
    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
    confirmacion.setTitle("Eliminar Producto");
    confirmacion.setHeaderText(null);
    confirmacion.setContentText("¿Estás seguro de que quieres eliminar a " + productoSeleccionado.getNombre() + "?");

    Optional<ButtonType> resultado = confirmacion.showAndWait();
        

    if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
        listaProductos.remove(productoSeleccionado); // Lo sacamos de la lista y desaparece de la tabla
    }
        
    tablaProductos.getSelectionModel().clearSelection();
    }
}

    @FXML
    private void modificar(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
}

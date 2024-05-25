/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.wernerparedes.dao.Conexion;
import org.wernerparedes.model.Compra;
import org.wernerparedes.model.DetalleCompra;
import org.wernerparedes.model.Producto;
import org.wernerparedes.system.Main;
import org.wernerparedes.utils.SuperKinalAlert;


public class MenuCompraController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TableView tblCompras;
    @FXML
    TableColumn colCompraId, colFecha, colTotal;
    @FXML
    Button btnVaciar,btnGuardar,btnRegresar;
    @FXML
    TextField tfCompraId, tfFechaCompra, tfTotalCompra;
    @FXML
    ComboBox cmbCompraId, cmbProductos;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
           stage.menuPrincipalView(); 
        }else if(event.getSource() == btnGuardar){
            if(tfCompraId.getText().equals("")){
                agregarCompra();
                agregarDetalleCompra();
                cargarDatos();
            }
        }else if(event.getSource() == btnVaciar){
            vaciarCampos();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        tfFechaCompra.setText(LocalDate.now().toString());
        cmbProductos.setItems(listarProductos());
        cmbCompraId.setItems(listarCompraId());
    }  
    
    public void cargarDatosEditar(Compra compra){
        Compra co = (Compra)tblCompras.getSelectionModel().getSelectedItem();
        if(co != null){
            tfCompraId.setText(Integer.toString(co.getCompraId()));
            tfTotalCompra.setText(Double.toString(co.getTotalCompra()));
        }
    }
    
    public void vaciarCampos(){
        tfCompraId.clear();
        tfTotalCompra.clear();
        
    }
    
    public void cargarDatos(){
        tblCompras.setItems(listarCompras());
        colCompraId.setCellValueFactory(new PropertyValueFactory<Compra, Integer>("compraId"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Compra, LocalDate>("fechaCompra"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Compra, Double>("totalCompra"));
    }
    
      public void agregarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarCompra(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setDouble(2, 0);
            statement.execute();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
      
          public ObservableList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
               int productoId = resultSet.getInt("productoId");
                String nombre = resultSet.getString("nombreProducto");
                String descripcion = resultSet.getString("descripcionProducto");
                int stock = resultSet.getInt("cantidadStock");
                double unidad = resultSet.getDouble("precioVentaUnitario");
                double mayor = resultSet.getDouble("precioVentaMayor");
                String distribuidor = resultSet.getString("Distribuidor");
                String categoria = resultSet.getString("Categoria");

                productos.add(new Producto(productoId, nombre, descripcion, stock, unidad, mayor, distribuidor,categoria));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
                
            }
        }
        return FXCollections.observableList(productos);
    }
    
    public ObservableList<Compra> listarCompras(){
            ArrayList<Compra> compras = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarCompras()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int compraId = resultSet.getInt("compraId");
                Date fecha = resultSet.getDate("fechaCompra");
                Double total = resultSet.getDouble("totalCompra");
                
                compras.add(new Compra(compraId, fecha.toLocalDate(), total));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(compras);
    }
     
    public ObservableList<Integer> listarCompraId() {
        HashSet<Integer> compraIds = new HashSet<>(); 

        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarDetallesCompra()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int compraId = resultSet.getInt("compraId");
                compraIds.add(compraId); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(new ArrayList<>(compraIds));
    }    
    
    public void agregarDetalleCompra() {
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarDetalleCompra(?,?)";
            statement = conexion.prepareStatement(sql);
            Integer compraSeleccionada = (Integer) cmbCompraId.getSelectionModel().getSelectedItem();
            Producto productoSeleccionado = (Producto) cmbProductos.getSelectionModel().getSelectedItem();
            
            if (compraSeleccionada != null && productoSeleccionado != null) {
                statement.setInt(1, compraSeleccionada);
                statement.setInt(2, productoSeleccionado.getProductoId());
                statement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
}


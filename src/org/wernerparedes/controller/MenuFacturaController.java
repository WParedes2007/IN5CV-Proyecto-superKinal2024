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
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.wernerparedes.dao.Conexion;
import org.wernerparedes.model.Cliente;
import org.wernerparedes.model.DetalleFactura;
import org.wernerparedes.model.Empleado;
import org.wernerparedes.model.Factura;
import org.wernerparedes.model.Producto;
import org.wernerparedes.system.Main;

/**
 * FXML Controller class
 *
 * @author hermanos_P2
 */
public class MenuFacturaController implements Initializable {

       private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    Button btnRegresar, btnGuardar, btnVaciar;
    
    @FXML
    TextField tfFacturaId, tfHora, tfTotal, tfFecha;
    
    @FXML
    ComboBox cmbCliente, cmbEmpleado, cmbFacturaId, cmbProductos;
    
    @FXML
    TableView tblFacturas;
    
    @FXML
    TableColumn colFacturaId, colCliente, colEmpleado, colFecha, colHora, colTotal;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfFacturaId.getText().equals("")){
                agregarFacturas();
                agregarDetalleFactura();
                cargarDatos();
        }
        }else if(event.getSource() == btnVaciar){
                vaciarCampos();
        }
    }
    
    
    public void vaciarCampos(){
        tfFacturaId.clear();
        tfTotal.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbEmpleado.getSelectionModel().clearSelection();
        
    }
    
    public void cargarDatos(){
        tblFacturas.setItems(listarFacturas());
        colFacturaId.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("facturaId"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Factura, LocalDate>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Factura, LocalTime>("hora"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Factura, Double>("total"));
        colCliente.setCellValueFactory(new PropertyValueFactory<Factura, String>("cliente"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<Factura, String>("empleado"));
        tblFacturas.getSortOrder().add(colFacturaId);
    }
   
    
    public ObservableList<Factura> listarFacturas(){
        ArrayList<Factura> facturas = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarFacturas()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int facturaId = resultset.getInt("facturaId");
                Date fecha = resultset.getDate("fecha");
                Time hora = resultset.getTime("hora");
                Double total = resultset.getDouble("total");
                String cliente = resultset.getString("cliente");
                String empleado = resultset.getString("empleado");

                
                facturas.add(new Factura(facturaId, fecha.toLocalDate(), hora.toLocalTime(), total, cliente, empleado));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                
            }
        }
        
        return FXCollections.observableList(facturas);
    }
    
    public ObservableList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarClientes()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int clienteId = resultset.getInt("clienteId");
                String nombre = resultset.getString("nombre");
                String apellido = resultset.getString("apellido");
                String telefono = resultset.getString("telefono");
                String direccion = resultset.getString("direccion");
                String nit = resultset.getString("nit");
                
                clientes.add(new Cliente(clienteId, nombre, apellido, telefono, direccion, nit));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
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
        return FXCollections.observableList(clientes);
    }
    
    public ObservableList<Empleado> listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int empleadoId = resultset.getInt("empleadoId");
                String nombreEmpleado = resultset.getString("nombreEmpleado");
                String apellidoEmpleado = resultset.getString("apellidoEmpleado");
                Double sueldo = resultset.getDouble("sueldo");
                String horaEntrada = resultset.getString("horaEntrada");
                String horaSalida = resultset.getString("horaSalida");
                String cargo = resultset.getString("cargo");
                String encargado = resultset.getString("encargado");

                empleados.add(new Empleado(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargo, encargado));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
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
        return FXCollections.observableList(empleados);
    }
    
    public void agregarFacturas(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarFactura(?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setTime(2, Time.valueOf(LocalTime.now()));
            statement.setDouble(3, 0);
            statement.setInt(4, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(5, ((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
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
     
    public ObservableList<Integer> listarFacturaId() {
        HashSet<Integer> facturaIds = new HashSet<>(); 

        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarDetallesFactura()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                int facturaId = resultset.getInt("facturaId");
                facturaIds.add(facturaId); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultset != null) {
                    resultset.close();
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
        return FXCollections.observableList(new ArrayList<>(facturaIds));
    }

    public void agregarDetalleFactura() {
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarDetalleFactura(?,?)";
            statement = conexion.prepareStatement(sql);
            Integer facturaSeleccionada = (Integer) cmbFacturaId.getSelectionModel().getSelectedItem();
            Producto productoSeleccionado = (Producto) cmbProductos.getSelectionModel().getSelectedItem();
            
            if (facturaSeleccionada != null && productoSeleccionado != null) {
                statement.setInt(1, facturaSeleccionada);
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
    
    public ObservableList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarProductos()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
               int productoId = resultset.getInt("productoId");
                String nombre = resultset.getString("nombreProducto");
                String descripcion = resultset.getString("descripcionProducto");
                int stock = resultset.getInt("cantidadStock");
                double unidad = resultset.getDouble("precioVentaUnitario");
                double mayor = resultset.getDouble("precioVentaMayor");
                String distribuidor = resultset.getString("Distribuidor");
                String categoria = resultset.getString("Categoria");

                productos.add(new Producto(productoId, nombre, descripcion, stock, unidad, mayor, distribuidor,categoria));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbFacturaId.setItems(listarFacturaId());
        cmbProductos.setItems(listarProductos());
        cmbCliente.setItems(listarClientes());
        cmbEmpleado.setItems(listarEmpleados());
        cargarDatos();
        tfFecha.setText(LocalDate.now().toString());
        tfHora.setText(LocalTime.now().toString());
    }  
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
       
    
}

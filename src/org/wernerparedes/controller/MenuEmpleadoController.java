/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.wernerparedes.dto.EmpleadoDTO;
import org.wernerparedes.model.Empleado;
import org.wernerparedes.system.Main;
import org.wernerparedes.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuEmpleadoController implements Initializable {
   private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    @FXML
    Button btnAgregar;
    @FXML
    Button btnEditar, btnEliminar, btnBuscar,btnGuardar, btnVaciar;
    @FXML
    Button btnRegresar;
    @FXML
    TextField tfEmpleadoId,tfEmpleado ;
    @FXML
    TableView tblEmpleados;
    @FXML
    TableColumn colEmpleadoId,colNombreEmpleado,colApellidoEmpleado,colSueldo,colEntrada,colSalida,colCargoId,colEncargado;
    @FXML
    ComboBox cmbEncargados;
    
    @FXML
    
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAgregar){
            stage.formEmpleadosView(1);
        }else if(event.getSource() == btnEditar){
            EmpleadoDTO.getEmpleadoDTO().setEmpleado((Empleado)tblEmpleados.getSelectionModel().getSelectedItem());
            stage.formEmpleadosView(2);
        }else if(event.getSource() == btnEliminar){
            int empId = ((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getEmpleadoId();
            eliminarEmpleado(empId);
            cargarDatos();
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if (event.getSource() == btnBuscar){
            tblEmpleados.getItems().clear();
            if(tfEmpleadoId.getText().equals("")){
                cargarDatos();
            }else{
                tblEmpleados.getItems().add(buscarEmpleado());
                colEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("empleadoId"));
                colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombreEmpleado"));
                colApellidoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellidoEmpleado"));
                colSueldo.setCellValueFactory(new PropertyValueFactory<Empleado, String>("sueldo"));
                colEntrada.setCellValueFactory(new PropertyValueFactory<Empleado, String>("horaEntrada"));
                colSalida.setCellValueFactory(new PropertyValueFactory<Empleado, String>("horaSalida"));
                colCargoId.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombreCargo"));
                colEncargado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("Encargado"));
            }
        } else if(event.getSource() == btnGuardar){
            editarEncargado();
            cargarDatos();
        } else if(event.getSource() == btnVaciar){
            vaciarCampos();
        }
    }
    
    public void vaciarCampos(){
        tfEmpleado.clear();
        cmbEncargados.getSelectionModel().clearSelection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbEncargados.setItems(listarEmpleados());
        cargarDatos();
    }  
    
    public void cargarDatos(){
                tblEmpleados.setItems(listarEmpleados());
                colEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleado, Integer> ("empleadoId"));
                colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String> ("nombreEmpleado"));
                colApellidoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String> ("apellidoEmpleado"));
                colSueldo.setCellValueFactory(new PropertyValueFactory<Empleado, String> ("sueldo"));
                colEntrada.setCellValueFactory(new PropertyValueFactory<Empleado, String> ("horaEntrada"));
                colSalida.setCellValueFactory(new PropertyValueFactory<Empleado, String> ("horaSalida"));
                colCargoId.setCellValueFactory(new PropertyValueFactory<Empleado, String> ("cargo"));
                colEncargado.setCellValueFactory(new PropertyValueFactory<Empleado, String> ("encargado"));
                tblEmpleados.getSortOrder().add(colEmpleadoId);

    }
    
    public void cargarDatosEditar(){
        Empleado ep = (Empleado)tblEmpleados.getSelectionModel().getSelectedItem();
        if(ep != null){
            tfEmpleado.setText(Integer.toString(ep.getEmpleadoId()));
            cmbEncargados.getSelectionModel().select(ObtenerIndexEmpleado());
        }
    }
    
    public ObservableList<Empleado> listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarEmpleados";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                Double sueldo = resultSet.getDouble("sueldo");
                String horaEntrada = resultSet.getString("horaEntrada");
                String horaSalida = resultSet.getString("horaSalida");
                String cargo = resultSet.getString("cargo");
                String encargado = resultSet.getString("encargado");

                empleados.add(new Empleado(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada,horaSalida,cargo,encargado));
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
        
        return FXCollections.observableList(empleados);
    }
    
    public void eliminarEmpleado(int empId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarEmpleado(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,empId);
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
    
    public Empleado buscarEmpleado(){
        Empleado empleado = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarEmpleado(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                double sueldo = resultSet.getDouble("sueldo");
                String horaEntrada = resultSet.getString("horaEntrada");
                String horaSalida = resultSet.getString("horaSalida");
                String cargo = resultSet.getString("nombreCargo");
                String encargado = resultSet.getString("encargado");
                
                empleado = (new Empleado(empleadoId,nombreEmpleado,apellidoEmpleado,sueldo,horaEntrada,horaSalida,cargo,encargado));
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
        return empleado;
    }
    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public void editarEncargado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_asignarEncargado(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleado.getText()));
            statement.setInt(2, ((Empleado)cmbEncargados.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.execute();
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
    }
    
    public int ObtenerIndexEmpleado(){
        int index = 0;
        for(int i = 0 ; i < cmbEncargados.getItems().size() ; i++){ 
            String encargadoCmb = cmbEncargados.getItems().get(i).toString();
            String empleadoTbl = ((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getEncargado();
            if(encargadoCmb.equals(empleadoTbl)){
                index = i;
                break;
            }
        }
        return index;
    }
      
    
}

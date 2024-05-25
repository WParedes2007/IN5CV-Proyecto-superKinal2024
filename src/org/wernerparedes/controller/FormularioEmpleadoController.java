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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.wernerparedes.dao.Conexion;
import org.wernerparedes.dto.EmpleadoDTO;
import org.wernerparedes.model.Cargo;
import org.wernerparedes.model.Empleado;
import org.wernerparedes.system.Main;
import org.wernerparedes.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormularioEmpleadoController implements Initializable {
    
    private Main stage;    
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet = null;
    
    @FXML
    ComboBox cmbCargos;
    @FXML
    TextField tfEmpleadoId, tfNombre, tfApellido, tfSueldo, tfEntrada, tfSalida;
    @FXML
    Button btnGuardar, btnCancelar;
    @FXML
    TableView tblEmpleados;
    
    

 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCargos.setItems(listarCargos());
        if(EmpleadoDTO.getEmpleadoDTO().getEmpleado() != null){
            cargarDatos(EmpleadoDTO.getEmpleadoDTO().getEmpleado());
        }
    }    
    public void cargarDatos(Empleado empleado){
        tfEmpleadoId.setText(Integer.toString(empleado.getEmpleadoId()));
        tfNombre.setText(empleado.getNombreEmpleado());
        tfApellido.setText(empleado.getApellidoEmpleado());
        double sueldo = empleado.getSueldo();
        tfSueldo.setText(Double.toString(sueldo));
        tfEntrada.setText(empleado.getHoraEntrada());
        tfSalida.setText(empleado.getHoraSalida());
        cmbCargos.getSelectionModel().select(obtenerIndexCargo()); 
    }
    
     public int obtenerIndexCargo(){
       int index = 0;
        for(int i = 0 ; i < cmbCargos.getItems().size() ; i++){
            String cargoIdCmb = cmbCargos.getItems().get(i).toString();
            String empleadoDTO = EmpleadoDTO.getEmpleadoDTO().getEmpleado().getCargo();
            if(cargoIdCmb.equals(empleadoDTO)){
                index = i;
                break;
            }
        }
        return index;
    }
     
     public void cargarCmbEstatus(){
        cmbCargos.getItems().add("");
    }
    
    
    public Main getStage() {
        return stage;
    }
 
    public void setStage(Main stage) {
        this.stage = stage;
    }
 
    public void setOp(int op) {
        this.op = op;
    }
    public void agregarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarEmpleado(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfApellido.getText());
            double sueldo = Double.parseDouble(tfSueldo.getText());
            statement.setDouble(3, sueldo);
            statement.setString(4, tfEntrada.getText());
            statement.setString(5, tfSalida.getText());
            statement.setInt(6,((Cargo)cmbCargos.getSelectionModel().getSelectedItem()).getCargoId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(conexion != null){
                    conexion.close();
                }else if(statement != null){
                    statement.close();
                }
            }catch(SQLException e){
            }
        }
    }
    public void editarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarEmpleado(?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfApellido.getText());
            double sueldo = Double.parseDouble(tfSueldo.getText());
            statement.setDouble(4, sueldo);
            statement.setString(5, tfEntrada.getText());
            statement.setString(6, tfSalida.getText());
            statement.setInt(7,((Cargo)cmbCargos.getSelectionModel().getSelectedItem()).getCargoId());
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
            }
        }
    }
    
    

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuEmpleadoView();
            EmpleadoDTO.getEmpleadoDTO().setEmpleado(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombre.getText().equals("") && !tfApellido.getText().equals("")&& !tfSueldo.getText().equals("")&& !tfEntrada.getText().equals("")&& !tfSalida.getText().equals("")){
                    agregarEmpleado();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                    stage.menuEmpleadoView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfNombre.requestFocus();
                    return;
                }
            }else if(op == 2){
                
                if(!tfNombre.getText().equals("") && !tfApellido.getText().equals("")&& !tfSueldo.getText().equals("")&& !tfEntrada.getText().equals("")&& !tfSalida.getText().equals("")){
                     if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarEmpleado();
                        EmpleadoDTO.getEmpleadoDTO().setEmpleado(null);
                        stage.menuEmpleadoView();
                     }
                    
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfNombre.requestFocus();
                    return;
                }
            }else if(op == 3){
                agregarEmpleado();
                stage.formUsuariosView();
            }
        }
    }
    
    
    public ObservableList<Cargo> listarCargos(){
        ArrayList<Cargo> cargos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCargos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int cargoId = resultSet.getInt("cargoId");
                String nomCargo = resultSet.getString("nombreCargo");
                String desCargo = resultSet.getString("descripcionCargo");
                
                cargos.add(new Cargo(cargoId, nomCargo, desCargo));
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
        return FXCollections.observableList(cargos);
    }
    
    
    
}

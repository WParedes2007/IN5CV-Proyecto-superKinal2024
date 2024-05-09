/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
    @FXML
    ComboBox cmbEmpleados, cmbCargos;
    @FXML
    TextField tfEmpleadoId, tfNombre, tfApellido, tfSueldo, tfEntrada, tfSalida, tfCargo, tfEncargado;
    @FXML
    Button btnGuardar, btnCancelar;
    @FXML
    TableView tblEmpleados;

 
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(EmpleadoDTO.getEmpleadoDTO().getEmpleado() != null){
            cargarDatos(EmpleadoDTO.getEmpleadoDTO().getEmpleado());
            //cmbEmpleados.setItems(listarEmpleados());
        }
    }    
    public void cargarDatos(Empleado empleado){
        tfEmpleadoId.setText(Integer.toString(empleado.getEmpleadoId()));
        tfNombre.setText(empleado.getNombreEmpleado());
        tfApellido.setText(empleado.getApellidoEmpleado());
        tfSueldo.setText(Double.toString(empleado.getSueldo()));
        tfEntrada.setText(empleado.getHoraEntrada());
        tfSalida.setText(empleado.getHoraSalida());
        tfCargo.setText(empleado.getCargo());
        tfEncargado.setText(empleado.getEncargado());
 
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
            String sql = "call sp_AgregarEmpleado(?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfApellido.getText());
            statement.setString(3, tfSueldo.getText());
            statement.setString(4, tfEntrada.getText());
            statement.setString(5, tfSalida.getText());
            //statement.setInt(7,((Cargo)cmbCargos.getSelectionModel().getSelectedItem()).getCargoId());
            //statement.setInt(8,((Empleado)cmbEmpleados.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.setString(6, tfCargo.getText());
            statement.setString(7, tfEncargado.getText());
 
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
            String sql = "call sp_EditarEmpleado(?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfApellido.getText());
            statement.setString(4, tfSueldo.getText());
            statement.setString(5, tfEntrada.getText());
            statement.setString(6, tfSalida.getText());
            //statement.setInt(7,((Cargo)cmbCargos.getSelectionModel().getSelectedItem()).getCargoId());
            //statement.setInt(8,((Empleado)cmbEmpleados.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.setString(7, tfCargo.getText());
            statement.setString(8, tfEncargado.getText());
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
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfNombre.requestFocus();
                    return;
                }
                //EmpleadoDTO.getEmpleadoDTO().setEmpleado(null);
                stage.menuEmpleadoView();
            }else if(op == 2){
                
                if(!tfNombre.getText().equals("") && !tfApellido.getText().equals("")&& !tfSueldo.getText().equals("")&& !tfEntrada.getText().equals("")&& !tfSalida.getText().equals("")){
                     if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarEmpleado();
                        EmpleadoDTO.getEmpleadoDTO().setEmpleado(null);
                        stage.menuClientesView();
                     }
                    
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfNombre.requestFocus();
                    return;
                }
            }
        }
    }
    
    /*public void cargarDatosEditar(){
        Empleado e = (Empleado)tblEmpleados.getSelectionModel().getSelectedItem();
        if(e != null){
            tfEmpleadoId.setText(Integer.toString(e.getEmpleadoId()));
            tfNombre.setText(e.getNombreEmpleado());
            tfApellido.setText(e.getApellidoEmpleado());
            tfSueldo.setText(Double.toString(e.getSueldo()));
            tfEntrada.setText(e.getHoraEntrada());
            tfEntrada.setText(e.getHoraSalida());
            cmbEmpleados.getSelectionModel().select(obtenerIndexCargo());
            cmbCargos.getSelectionModel().select(obtenerIndexEmpleado());
        }
    }
    
    public int obtenerIndexEmpleado(){
        int index = 0;
        for(int i = 0 ; i <= cmbEmpleados.getItems().size() ; i++){
            String empleadoCmb = cmbEmpleados.getItems().get(i).toString();
            String empleadoTbl = ((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getEncargado();
            if(empleadoCmb.equals(empleadoTbl)){
                index = i;
                break;
            }
        }
        return index;
    }
    
     public int obtenerIndexCargo(){
        int index = 0;
        for(int i = 0 ; i <= cmbEmpleados.getItems().size() ; i++){
            String cargoCmb = cmbEmpleados.getItems().get(i).toString();
            String cargoTbl = ((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCargo();
            if(cargoCmb.equals(cargoTbl)){
                index = i;
                break;
            }
        }
        return index;
    }*/
    
}

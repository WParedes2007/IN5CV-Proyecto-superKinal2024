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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.wernerparedes.dao.Conexion;
import org.wernerparedes.dto.CompraDTO;
import org.wernerparedes.model.Compra;
import org.wernerparedes.system.Main;
import org.wernerparedes.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author hermanos_P2
 */
public class FormularioCompraController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    
    @FXML
    TextField tfCompraId, tfFechaCompra, tfTotalCompra;
        
    @FXML
    Button btnGuardar, btnCancelar;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CompraDTO.getCompraDTO().getCompra() != null){
            tfFechaCompra.setText(LocalDate.now().toString());
            cargarDatos(CompraDTO.getCompraDTO().getCompra());
        }
    }
    
    public void agregarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarCompra(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
        //statement.setDate(1, Date.valueOf(LocalDate.now(tfFechaCompra)));
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
    
    public void cargarDatos(Compra compra){
        tfCompraId.setText(Integer.toString(compra.getCompraId()));
        tfTotalCompra.setText(Double.toString(compra.getTotalCompra()));
    }
    
    public void editarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarCompra(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCompraId.getText()));
            statement.setDate(2, Date.valueOf(tfFechaCompra.getText()));
            statement.setString(3, tfTotalCompra.getText());
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

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuCompraView();
            CompraDTO.getCompraDTO().setCompra(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfFechaCompra.getText().equals("") && !tfTotalCompra.getText().equals("")){
                    
                agregarCompra();
                SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                stage.menuCompraView(); 
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfFechaCompra.requestFocus();
                    return;
                }  
            }else if(op == 2){
                if(!tfFechaCompra.getText().equals("") && !tfTotalCompra.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarCompra();
                        CompraDTO.getCompraDTO().setCompra(null);
                        stage.menuCompraView();   
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfFechaCompra.requestFocus();
                    return;
                }
                
            }

        }
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
    
    
}

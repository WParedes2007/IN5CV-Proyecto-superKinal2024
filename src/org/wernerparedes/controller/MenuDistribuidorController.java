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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.wernerparedes.dao.Conexion;
import org.wernerparedes.dto.DistribuidorDTO;
import org.wernerparedes.model.Distribuidor;
import org.wernerparedes.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuDistribuidorController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    @FXML
    Button btnAgregar;
    @FXML
    Button btnEditar, btnEliminar, btnBuscar;
    @FXML
    Button btnRegresar;
    @FXML
    TextField tfDistribuidorId;
    @FXML
    TableView tblDistribuidor;
    @FXML
    TableColumn colDistribuidorId,colNombre,colTelefono,colDireccion,colNit,colWeb;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void cargarLista(){
       tblDistribuidor.getItems().add(listarDistribuidores());
       colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Distribuidor, Integer> ("distribuidorId"));
       colNombre.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("nombreDistribuidor"));
       colDireccion.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("direccionDistribuidor"));
       colNit.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("nitDistribuidor"));
       colTelefono.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("telefonoDistribuidor"));
       colWeb.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("web"));

    }
    
    public ObservableList<Distribuidor>listarDistribuidores(){
        ArrayList<Distribuidor> distribuidores = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarDistribuidores";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombre = resultSet.getString("nombreDistribuidor");
                String direccion = resultSet.getString("direccionDistribuidor");
                String nit = resultSet.getString("nitDistribuidor");
                String telefono = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
                
                distribuidores.add(new Distribuidor(distribuidorId,nombre,direccion,nit,telefono, web));
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
        return FXCollections.observableList(distribuidores);
    }
    
    public void eliminarDistribuidor(int disId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarDistribuidor(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,disId);
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
    
    public Distribuidor buscarDistribuidor(){
        Distribuidor distribuidor = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarDistribuidor(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfDistribuidorId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombre = resultSet.getString("nombreDistribuidor");
                String direccion = resultSet.getString("direccionDistribuidor");
                String nit = resultSet.getString("nitDistribuidor");
                String telefono = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
                
                distribuidor = (new Distribuidor(distribuidorId,nombre,direccion, nit,telefono,web));
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
        return distribuidor;
    }
    
    public void handleButtonAction(ActionEvent event){
        if(event.getSource()== btnAgregar){
                //stage.formClienteView(1);
                }else if(event.getSource()== btnEditar){
                DistribuidorDTO.getDistribuidorDTO().setDistribuidor((Distribuidor)tblDistribuidor.getSelectionModel().getSelectedItem());
                //stage.formClienteView(2);
                }else if(event.getSource()== btnEliminar){
                    int disId = ((Distribuidor)tblDistribuidor.getSelectionModel().getSelectedItem()).getDistribuidorId();
                    eliminarDistribuidor(disId);
                    cargarLista();
                }else if(event.getSource()== btnBuscar){
                    tblDistribuidor.getItems().clear();

                    if(tfDistribuidorId.getText().equals("")){
                        cargarLista();
                    }else{

                    tblDistribuidor.getItems().add(buscarDistribuidor());
                    colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Distribuidor, Integer> ("distribuidorId"));
                    colNombre.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("nombreDistribuidor"));
                    colDireccion.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("direccionDistribuidor"));
                    colNit.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("nitDistribuidor"));
                    colTelefono.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("telefonoDistribuidor"));
                    colWeb.setCellValueFactory(new PropertyValueFactory<Distribuidor, String> ("web"));

                    }
                }else if(event.getSource()== btnRegresar){
                stage.menuPrincipalView();
                }  
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}

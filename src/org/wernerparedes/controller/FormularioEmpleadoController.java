/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.wernerparedes.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormularioEmpleadoController implements Initializable {
    private Main stage;
    private int op;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

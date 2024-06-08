/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.report;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.wernerparedes.dao.Conexion;
import win.zqxu.jrviewer.JRViewerFX;

/**
 *
 * @author informatica
 */
public class GenerarReporte {
    private static GenerarReporte instance;
    
    private static Connection conexion = null;
    
    private GenerarReporte(){
        
    }
    

    public static GenerarReporte getInstance() {
        if(instance == null){
            instance = new GenerarReporte();
        }
        return instance;
    }
    
    
    
    public void generarFactura(int factId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("factId", factId);
            
            InputStream jasperPath = GenerarReporte.class.getResourceAsStream("/org/wernerparedes/report/Factura.jasper");
            JasperPrint reporte = JasperFillManager.fillReport(jasperPath, parametros, conexion);
            
            Stage reportStage = new Stage();
            
            JRViewerFX reportViewer = new JRViewerFX(reporte);
            
            Pane root = new Pane();
            
            root.getChildren().add(reportViewer);
            
            reportViewer.setPrefSize(612,792);
            
            Scene scene = new Scene(root);
            reportStage.setScene(scene);
            reportStage.setTitle("Factura");
            reportStage.show();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void generarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            
            Map<String, Object> parametros = new HashMap<>();
            
            InputStream jasperPath = GenerarReporte.class.getResourceAsStream("/org/wernerparedes/report/Cliente.jasper");
            JasperPrint reporte = JasperFillManager.fillReport(jasperPath, parametros, conexion);
            
            Stage reportStage = new Stage();
            
            JRViewerFX reportViewer = new JRViewerFX(reporte);
            
            Pane root = new Pane();
            
            root.getChildren().add(reportViewer);
            
            reportViewer.setPrefSize(912,400);
            
            Scene scene = new Scene(root);
            reportStage.setScene(scene);
            reportStage.setTitle("Cliente");
            reportStage.show();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void generarProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            
            Map<String, Object> parametros = new HashMap<>();
            
            InputStream jasperPath = GenerarReporte.class.getResourceAsStream("/org/wernerparedes/report/Producto.jasper");
            JasperPrint reporte = JasperFillManager.fillReport(jasperPath, parametros, conexion);
            
            Stage reportStage = new Stage();
            
            JRViewerFX reportViewer = new JRViewerFX(reporte);
            
            Pane root = new Pane();
            
            root.getChildren().add(reportViewer);
            
            reportViewer.setPrefSize(1212,600);
            
            Scene scene = new Scene(root);
            reportStage.setScene(scene);
            reportStage.setTitle("Producto");
            reportStage.show();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hermanos_P2
 */
public class Conexion {
    private static Conexion instance;
    
    private String url = "jdbc:mysql://localhost:3306/superKinalIN5CVDB?serverTimeZone=GMT-6&useSSL=false";
    private String user = "Shiro";
    //private String user = "wernerParedes";
    //private String password = "MCRTBP";
    private String password = "1234";
    
    private Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static Conexion getInstance(){
        if(instance == null){
            instance = new Conexion();
           
        }
        return instance;
    }
    public Connection obtenerConexion()throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.model;

/**
 *
 * @author informatica
 */
public class Usuario {
    private int usuarioId;
    private String usuario;
    private String contrasenia;
    private int nivelAccesoId;
    private int empleado;

    public Usuario() {
    }

    public Usuario(int usuarioId, String usuario, String contrasenia, int nivelAccesoId, int empleado) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nivelAccesoId = nivelAccesoId;
        this.empleado = empleado;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getNivelAccesoId() {
        return nivelAccesoId;
    }

    public void setNivelAccesoId(int nivelAccesoId) {
        this.nivelAccesoId = nivelAccesoId;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    

    /*@Override
    public String toString() {
        return "{Id: " + usuarioId + " } {Usuario: " + usuario + '}';
    }*/

    @Override
    public String toString() {
        return "Usuario{" + "usuarioId=" + usuarioId + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", nivelAccesoId=" + nivelAccesoId + ", empleado=" + empleado + '}';
    }
    
    
    
    
    
}

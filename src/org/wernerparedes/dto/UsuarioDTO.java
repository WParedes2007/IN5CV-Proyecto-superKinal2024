/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.dto;

import org.wernerparedes.model.Usuario;

/**
 *
 * @author hermanos_P2
 */
public class UsuarioDTO {
    private static UsuarioDTO instance;
    private Usuario usuario;
    
    private UsuarioDTO(){
        
    }
    
    public static UsuarioDTO getUsuarioDTO(){
        if(instance == null){
            instance = new UsuarioDTO();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    
}

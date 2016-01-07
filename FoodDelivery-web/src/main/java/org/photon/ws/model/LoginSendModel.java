/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws.model;

public class LoginSendModel {
    private String email ;
    private Long id ;

    public LoginSendModel() {
    }

    public LoginSendModel( Long id,String email) {
        this.email = email;
        this.id = id;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}

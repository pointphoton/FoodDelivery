/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws.model;

import javax.enterprise.inject.Model;

@Model
public class LoginModel {
    
    String email ;
    String password;
    

    public LoginModel() {
    }

    public LoginModel(String mail , String pass) {
     this.email = mail;
     this.password = pass;
   
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}

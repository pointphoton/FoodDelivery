/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws.model;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.photon.ejb.CustomerFacadeLocal;
import org.photon.ws.ProductsResource;


@Model
public class AccountModel {
  
  

    private String userName ;
    private String password ;
    private String email ;
    


    public AccountModel() {
              
    }

    public AccountModel(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    



    

 

    
    
}

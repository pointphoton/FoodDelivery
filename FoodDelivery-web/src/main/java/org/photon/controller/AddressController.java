/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.controller;


import java.io.Serializable;
import javax.ejb.EJB;
import org.photon.ejb.AddressFacadeLocal;
public class AddressController implements Serializable{
         @EJB(beanName = "AddressEJB")
    private AddressFacadeLocal addressEJB;
    
}

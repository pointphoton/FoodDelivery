/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.controller;


import java.io.Serializable;
import javax.ejb.EJB;
import org.photon.ejb.EmployeeDeliveryFacadeLocal;

/**
 *
 * @author Photon
 */
public class EmployeeDelivery implements Serializable{
    
    @EJB(beanName="EmployeeDeliveryEJB")
    private EmployeeDeliveryFacadeLocal employeeDeliveryEJB ;
}

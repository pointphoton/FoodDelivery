/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.controller;


import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.photon.ejb.CustomerFacadeLocal;
import org.photon.entity.Customer;

@Named("custCont")
@ViewScoped
public class CustomerController implements Serializable{
    
    @EJB(beanName =  "CustomerEJB")
    private CustomerFacadeLocal customerEJB;
    private Customer customer;
    
    @PostConstruct
    public void init(){
    customer = new Customer();
    }
    
    public void addCustomer(){
    customerEJB.create(customer);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    
    
}

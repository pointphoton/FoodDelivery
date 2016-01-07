/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import org.photon.ejb.AddressFacadeLocal;
import org.photon.ejb.CustomerFacadeLocal;
import org.photon.ejb.UserFacadeLocal;
import org.photon.entity.Address;
import org.photon.entity.Customer;
import org.photon.entity.User;
import org.photon.entity.util.Role;

@Named("userCont")
@SessionScoped
public class UserController implements Serializable {

    @EJB(beanName = "CustomerEJB")
    private CustomerFacadeLocal customerEJB;
    @EJB(beanName = "UserEJB")
    private UserFacadeLocal userEJB;
    @EJB(beanName = "AddressEJB")
    private AddressFacadeLocal addressEJB;

    private Address address1;
    private Address address2;
    private Customer custo;
    private Customer lgnCustomer;

    private User registeredUser;
    private User user;

    @PostConstruct
    public void init() {
        user = new User();
        registeredUser = new User();
        custo = new Customer();
        address1 = new Address();
        address2 = new Address();
        lgnCustomer = new Customer();
    }

    public String createAdminLogin() {
user.setName("abcd");
user.setPassword("12345");
user.setEmail("admin@admin.com");
user.setUserName("admin");
        user.setRole(Role.ADMIN.getValue());
        userEJB.create(user);
        return "index?faces-redirect=true";
    }

    public String confirmSignUp() {
        try {
            user.setRole(Role.USER.getValue());
            userEJB.create(user);
            System.out.println("saved user id : " + user.getUserId());
            registeredUser = userEJB.find(user.getUserId());
            custo.setName(registeredUser.getName());
            custo.setUserName(registeredUser.getUserName());
            custo.setEmail(registeredUser.getEmail());
            custo.setPassword(registeredUser.getPassword());
            try {
                customerEJB.create(custo);
            } catch (Exception e) {
                //
            }

        } catch (Exception e) {
            //
        }
        return "customerpage";
    }

    public String updateCustInfo() {
        System.out.println("custo id *** " + custo.getCustomerId());
        address1.setCustomer(custo);
        address2.setCustomer(custo);
        addressEJB.create(address1);
        addressEJB.create(address2);
        Set<Address> collection = new HashSet();
        collection.add(address1);
        collection.add(address2);
        custo.setAddresses(collection);
        customerEJB.edit(custo);
 return "shopping?faces-redirect=true";
    }

    public String confirmSignIn(){
        System.out.println(" user name "+  lgnCustomer.getUserName()+"  pass " +lgnCustomer.getPassword());
  if(      customerEJB.confirmCustomer(   lgnCustomer.getUserName(), lgnCustomer.getPassword()) )
  {
   return "shopping?faces-redirect=true";
  }
  FacesContext.getCurrentInstance().addMessage("messagesConfirm",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : ","Invalid user name or password!."));
    
        return "";
    }
    
    


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(User registeredUser) {
        this.registeredUser = registeredUser;
    }

    public Address getAddress1() {
        return address1;
    }

    public void setAddress1(Address address1) {
        this.address1 = address1;
    }

    public Address getAddress2() {
        return address2;
    }

    public void setAddress2(Address address2) {
        this.address2 = address2;
    }

    public Customer getCusto() {
        return custo;
    }

    public void setCusto(Customer custo) {
        this.custo = custo;
    }

    public Customer getLgnCustomer() {
        return lgnCustomer;
    }

    public void setLgnCustomer(Customer lgnCustomer) {
        this.lgnCustomer = lgnCustomer;
    }

}


/*
 public boolean checkAvailable(AjaxBehaviorEvent event) {
 InputText inputText = (InputText) event.getSource();
 String value = (String) inputText.getValue();
 System.out.println("value string "+value);
 boolean available = userEJB.checkAvailable(value);
 if (!available) {
 String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "userExistsMsg").getString("userExistsMsg");
 FacesMessage message = new FacesMessage(null, errorMessage, errorMessage);
 //    FacesMessage message = constructErrorMessage(null, String.format(FacesContext.getCurrentInstance().getApplication().getMessageBundle().g("userExistsMsg"), value));
 FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), message);
 } else {
 //  FacesMessage message = constructInfoMessage(null, String.format(getMessageBundle().getString("userAvailableMsg"), value));
 //    FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), message);
 String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "userAvailableMsg").getString("userAvailableMsg");
 FacesMessage message = new FacesMessage(null, errorMessage, errorMessage);
 FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), message);
 }

 return available;
 }
 */

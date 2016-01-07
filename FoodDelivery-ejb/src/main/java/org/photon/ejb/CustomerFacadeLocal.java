/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ejb;


import java.util.List;
import javax.ejb.Local;
import org.photon.entity.Customer;

/**
 *
 * @author Photon
 */
@Local
public interface CustomerFacadeLocal {

    void create(Customer customer);
    
     void edit(Customer customer);

    void remove(Customer customer);

    Customer find(Long id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    int count();
    
   Long save (Customer customer);
    
    Customer findByMailAndPass(String email , String password);
    
    boolean  confirmCustomer(String userName, String password);
    
    boolean confirmEmailPassword(String email,String password);
    
    boolean confirmUserName(String userName,String password);
    
    boolean checkUserName(String userName);
    
    boolean checkEmail(String email);
    
       boolean confirmToken(String token);
    
    boolean confirmToken(String email,String token);
    
    
}

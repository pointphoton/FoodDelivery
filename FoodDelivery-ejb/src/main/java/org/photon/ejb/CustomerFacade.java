/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ejb;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.photon.entity.Customer;

/**
 *
 * @author Photon
 */
@Stateless(name = "CustomerEJB")
public class CustomerFacade extends AbstractFacade<Customer> implements CustomerFacadeLocal {

    
    private static final String  USER_NAME = "userName";
    private static final String  PASSWORD = "password";
    private static final String  EMAIL = "email";
    private static final String  TOKEN= "token";

    @PersistenceContext(unitName = "BitirmeProjesiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
       
    }

    @Override
    public boolean confirmCustomer(String userName, String password) {
        Session session = em.unwrap(Session.class);
        int size = session.createCriteria(Customer.class)
                .add(Restrictions.like(USER_NAME, userName))
                .add(Restrictions.like(PASSWORD, password)).list().size();
        System.out.println("size of customer list : " + size);
        return size == 1;

 //    Query query = session.createQuery("select count(*) from customer  u where u.username = :userName  AND u.password = :password").setParameter("userName", userName).setParameter("password", password);
    }

    @Override
    public boolean confirmEmailPassword(String email, String password) {
     Session session = em.unwrap(Session.class);
        Criteria crt =   session.createCriteria(Customer.class);
     int size =   crt.add(Restrictions.like(EMAIL, email))
                .add(Restrictions.like(PASSWORD,password))
                .list().size();
        return size==1;  
    }

    @Override
    public boolean confirmUserName(String userName, String password) {
          Session session = em.unwrap(Session.class);
        Criteria crt =   session.createCriteria(Customer.class);
     int size =   crt.add(Restrictions.like(USER_NAME, userName))
                .add(Restrictions.like(PASSWORD,password))
                .list().size();
        return size==1;  
    
    }

    @Override
    public boolean checkUserName(String userName) {
         Session session = em.unwrap(Session.class);
        Criteria crt =   session.createCriteria(Customer.class);
     int size =   crt.add(Restrictions.like(USER_NAME, userName))
                     .list().size();
        return size==0;     }

    @Override
    public boolean checkEmail(String email) {
        Session session = em.unwrap(Session.class);
        Criteria crt =   session.createCriteria(Customer.class);
     int size =   crt.add(Restrictions.like(EMAIL, email))
                     .list().size();
        return size==0;     }

    @Override
    public boolean confirmToken(String token) {
           Session session = em.unwrap(Session.class);
        Criteria crt =   session.createCriteria(Customer.class);
     int size =   crt.add(Restrictions.like(TOKEN, token)).list().size();
        return size==1;  
    }
    
        @Override
    public boolean confirmToken(String email,String token) {
           Session session = em.unwrap(Session.class);
        Criteria crt =   session.createCriteria(Customer.class);
     int size =   crt.add(Restrictions.like(EMAIL, email)).
             add(Restrictions.like(TOKEN, token)).list().size();
        return size==1;  
    }

    @Override
    public Customer findByMailAndPass(String email, String password) {
        
           Session session = em.unwrap(Session.class);
        Criteria crt =   session.createCriteria(Customer.class);
       Customer customer = null ;
        try {
            customer = (Customer)crt.add(Restrictions.like(EMAIL, email))
                .add(Restrictions.like(PASSWORD,password)).uniqueResult();
        } catch (Exception ex) {
              Logger.getLogger(CustomerFacade.class.getName()).log(Level.SEVERE, null, ex+"uniqresult problem");
        }
    return customer ;
             
          }

    @Override
    public Long save(Customer customer) {
      Session session = em.unwrap(Session.class);
      session.save(customer);
      return customer.getCustomerId();
    }

}

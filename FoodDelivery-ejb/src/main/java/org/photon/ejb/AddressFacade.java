/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ejb;


import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.photon.entity.Address;
import org.photon.entity.Customer;

@Stateless(name ="AddressEJB")
public class AddressFacade extends AbstractFacade<Address> implements AddressFacadeLocal {
   @PersistenceContext(unitName = "BitirmeProjesiPU")
         private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }

    @Override
    public List<Address> findByCustomerId(Long id) {
   Query query= em.createNativeQuery("SELECT * FROM BPROJE.ADDRESS A   WHERE A.CUST_ID = :id ", Address.class).setParameter("id", id);
    List<Address> list =query.getResultList();
        System.out.println(" "+list.get(0).getAddresstext());      
    return list;
    }
    
}

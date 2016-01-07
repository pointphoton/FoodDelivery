/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ejb;


import java.util.List;
import javax.ejb.Local;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.photon.entity.Product;
import org.photon.lazymodel.repository.ProductFilter;

@Local
public interface SessionFacadeLocal {
    
    EntityManager getEntityManager();
    
    void write();
    
    Session getSession();
    
     public List<Product> filterProducts(ProductFilter filter);
    
    public  int quantityFilter(ProductFilter filter);
}

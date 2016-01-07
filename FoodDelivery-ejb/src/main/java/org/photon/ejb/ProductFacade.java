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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.photon.entity.Product;

/**
 *
 * @author Photon
 */
@Stateless(name = "ProductEJB")
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {
  @PersistenceContext(unitName = "BitirmeProjesiPU")
         private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public List<Product> findAllPaginated(int start, int size) {
        Session session =  em.unwrap(Session.class);
        
        Criteria criteria = session.createCriteria(Product.class);
            criteria.setFirstResult(start);
            criteria.setMaxResults(size);

     return       criteria.list();
    }
    
}

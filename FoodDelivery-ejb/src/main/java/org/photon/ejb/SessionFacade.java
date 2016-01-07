/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.photon.entity.Product;
import org.photon.lazymodel.repository.ProductFilter;

 @Stateless(name ="SessionEJB")
public class SessionFacade implements SessionFacadeLocal{

   @PersistenceContext(unitName = "BitirmeProjesiPU")
         private EntityManager em;


   @Override
    public  EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void write() {
        System.out.println("write Session ejb ");
    }

    @Override
    public Session getSession() {
        try {
             return em.unwrap(Session.class);
        } catch (Exception e) {
            System.out.println("session couldn't open.");
        }
  return null;
    }
    
        @SuppressWarnings("unchecked")
        @Override
    public List<Product> filterProducts(ProductFilter filter) {

        Criteria criteria1 = criteriaFilter(filter);

        criteria1.setFirstResult(filter.getFirstRecord());
        criteria1.setMaxResults(filter.getQuantitiyOfRecords());

        if (filter.isAscending() && filter.getPropriedadeOrdenacao() != null) {
            criteria1.addOrder(Order.asc(filter.getPropriedadeOrdenacao()));
        } else if (filter.getPropriedadeOrdenacao() != null) {
            criteria1.addOrder(Order.desc(filter.getPropriedadeOrdenacao()));
        }

        return criteria1.list();
    }

    public int quantityFilter(ProductFilter filter) {
        Criteria criteria = criteriaFilter(filter);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).intValue();
    }

    private Criteria criteriaFilter(ProductFilter filter) {
       Session session=null;
        try {
          
              session = em.unwrap(Session.class);
            //session=sessionEjb.getSession();
           boolean b=    session.isOpen();
            if (b) {
                System.out.println("open 60");
            }
            else
                 System.out.println("close 63");
        } catch (Exception e) {
            if (session==null) {
                    System.out.println("session null 66");
            }
            else{/*
                boolean b=    session.isOpen();
            if (b) {
                System.out.println("open 71");
            }
            else
                 System.out.println("close 74");
        */}
        }
        
        Criteria criteria = session.createCriteria(Product.class);
        System.out.println("_____________*line 87----------- ");
        if (StringUtils.isNotEmpty(filter.getName())) {
            criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
        }
   System.out.println("_____________*line 91----------- ");
        return criteria;
    }
    
}

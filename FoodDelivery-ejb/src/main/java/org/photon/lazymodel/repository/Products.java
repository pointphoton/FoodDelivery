package org.photon.lazymodel.repository;


import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.photon.ejb.SessionFacadeLocal;
import org.photon.entity.Product;


public class Products implements Serializable {
/*
    //   @EJB(beanName = "SessionEJB")
   // private SessionFacadeLocal sessionEJB ;
  private  EntityManager em ;
  private final SessionFacadeLocal sessionEjb;


  public Products() throws NamingException{
//  this.em = em;
  sessionEjb = (SessionFacadeLocal)new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/SessionEJB");
// em=sessionEjb.getEntityManager();
  }

  
    @SuppressWarnings("unchecked")
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
            sessionEjb.write();
              session = sessionEjb.getEntityManager().unwrap(Session.class);
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
        */

/*}
        }
        
        Criteria criteria = session.createCriteria(Product.class);
        System.out.println("_____________*line 87----------- ");
        if (StringUtils.isNotEmpty(filter.getName())) {
            criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
        }
   System.out.println("_____________*line 91----------- ");
        return criteria;
    }
*/
}

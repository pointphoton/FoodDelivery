/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.controller;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.photon.ejb.BranchFacadeLocal;
import org.photon.ejb.CategoryFacadeLocal;
import org.photon.ejb.ProductFacadeLocal;
import org.photon.entity.Branch;
import org.photon.entity.Category;
import org.photon.entity.Product;

@Named("defaultCont")
@ApplicationScoped
public class DefaultController {
    
    @ApplicationScoped
    private static int counter = 1; 
    
      @EJB(beanName = "ProductEJB")
    private ProductFacadeLocal productEJB;
      
       @EJB(beanName = "CategoryEJB")
    private CategoryFacadeLocal categoryEJB;
       
         @EJB(name = "BranchEJB")
   private BranchFacadeLocal branchEJB;

       @PostConstruct
     public  void init(){
           if (counter==1) {
               load();
               counter++;
           }
     
       }
     
     public void load(){
       Category  category = new Category();
       category.setCategoryName("Meat");
       category.setDescription("Meat Desc.");
       categoryEJB.create(category);
     Product   p = new Product();
            p.setName("Kebab");
            p.setProDescription("Kebap Desc.");
            p.setCurrentPrice(new BigDecimal((25)));
            p.setIsActive("Y");
       p.setCategory(category);
        Branch   branch = new Branch("Merkez","İstanbul Anadolu");
            branchEJB.create(branch);
     }
    
}

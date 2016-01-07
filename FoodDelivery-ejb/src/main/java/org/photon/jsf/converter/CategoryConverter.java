/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.jsf.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.photon.ejb.CategoryFacadeLocal;
import org.photon.entity.Category;

@ManagedBean
@RequestScoped
public class CategoryConverter implements Converter {
    
  //@EJB(beanName = "CategoryEJB")
 private CategoryFacadeLocal categoryEJB;
    
     public CategoryConverter() {
     try {
         categoryEJB = (CategoryFacadeLocal)new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/CategoryEJB");
     } catch (NamingException ex) {
         Logger.getLogger(CategoryConverter.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("converter "+value);
        if (value == null||value.isEmpty()) {
            return null;
        }
     
        try {
          
            return categoryEJB.find(Long.valueOf(value));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", value)), e);
        }
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
     if (!(modelValue instanceof Category)) return null;
    return ((Category) modelValue).toString();
    }
    

}

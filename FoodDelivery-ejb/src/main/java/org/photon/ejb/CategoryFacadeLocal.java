/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ejb;


import java.util.List;
import javax.ejb.Local;
import org.hibernate.validator.constraints.Range;
import org.photon.entity.Category;

/**
 *
 * @author Photon
 */
@Local
public interface CategoryFacadeLocal {

 public  void create(Category category);

public    void edit(Category category);

 public   void remove(Category category);

public    Category find(Long id);


   public List<Category> findAll();

public    List<Category> findRange(int[] range);

public    int count();
    
}

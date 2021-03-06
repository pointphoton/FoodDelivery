/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ejb;

import java.util.List;
import javax.ejb.Local;
import org.photon.entity.User;

@Local
public interface UserFacadeLocal {
    
      void create(User customer);

    void edit(User customer);

    void remove(User customer);

    User find(Long id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
    boolean checkAvailable(String userName) ;
    
}


package org.photon.ejb;


import java.util.List;
import javax.ejb.Local;
import org.photon.entity.Address;
import org.photon.entity.Customer;
@Local
public interface AddressFacadeLocal {

    void create(Address address);

    void edit(Address address);

    void remove(Address address);

    Address find(Long id);

    List<Address> findAll();

    List<Address> findRange(int[] range);

    int count();
    
     List<Address> findByCustomerId(Long id);
    
}

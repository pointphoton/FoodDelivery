package org.photon.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressseq")
    @SequenceGenerator(name = "addressseq", sequenceName = "ADDRESS_SEQ", initialValue = 1,allocationSize = 1)
    @Column(name = "address_id", unique = true, nullable = false)
    private Long addressid;
    @Column(name = "city", length = 30)
    private String city;
    @Column(name = "district", length = 40)
    private String district;
    @Column(name = "country", length = 40)
    private String country;
    @Column(name = "addresstext", length = 150)
    private String addresstext;
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE} , targetEntity = Order.class)
  @JoinColumn(name = "addr_id")
    private Set<Order> orders;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE} , targetEntity = Customer.class)
  @JoinColumn(name = "cust_id")
    private Customer customer;

    public Address() {
    }

 

    public Long getAddressid() {
        return addressid;
    }

    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddresstext() {
        return addresstext;
    }

    public void setAddresstext(String addresstext) {
        this.addresstext = addresstext;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }





    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

   

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressid == null) ? 0 : addressid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Address other = (Address) obj;
        if (addressid == null) {
            if (other.addressid != null) {
                return false;
            }
        } else if (!addressid.equals(other.addressid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "addressid=" + addressid + '}';
    }

}

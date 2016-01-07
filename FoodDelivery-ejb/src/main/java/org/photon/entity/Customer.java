package org.photon.entity;

import java.io.Serializable;
import java.util.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name="customer")
@XmlRootElement
public class Customer implements  Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeq")
    @SequenceGenerator(name = "customerSeq", sequenceName = "CUSTOMER_SEQ",initialValue = 1, allocationSize = 1)
    @Column(name = "customer_id", unique = true, nullable = false)
    private Long customerId;
     @Column(name = "name", length = 45)
    private String name;
     @Column(name = "surname", length = 45)
    private String surname;
       @Column(name = "tel_mobile", length = 45)
    private String mobilePhone;
           @Column(name = "home_mobile", length = 45)
    private String homePhone;
      @Column(name = "gender" , length = 1)
    private String gender;
    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "email", length = 150)
    private String email;
    @Column(name = "is_active", length = 1)
    private String isActive;
    @Column(name = "user_name", length = 45)
    private String userName;
    @Column(name = "password", length = 45)
    private String password;
    @Column(name = "token", length = 250)
    private String token ;
       @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE} ,targetEntity = Order.class)
         @JoinColumn(name = "cust_id")
    private Set<Order> orders ;
       @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE} ,targetEntity = Address.class)
         @JoinColumn(name = "cust_id")
    private Set<Address> addresses ;

    public Customer() {
    }

    public Customer(Long customerId, String name, String surname, String mobilePhone, String homePhone, String gender, Date birthdate, String email, String isActive, String userName, String password, String token, Set<Order> orders, Set<Address> addresses) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
        this.isActive = isActive;
        this.userName = userName;
        this.password = password;
        this.token = token;
        this.orders = orders;
        this.addresses = addresses;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

     
    
    

    @XmlTransient
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    
    @XmlTransient
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    
    
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
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
        Customer other = (Customer) obj;
        if (customerId == null) {
            if (other.customerId != null) {
                return false;
            }
        } else if (!customerId.equals(other.customerId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + "}"  ;
    }

}

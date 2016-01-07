/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import org.photon.ejb.AddressFacadeLocal;
import org.photon.ejb.BranchFacadeLocal;
import org.photon.ejb.CustomerFacadeLocal;
import org.photon.ejb.OrderFacadeLocal;
import org.photon.ejb.ProductFacadeLocal;
import org.photon.entity.Address;
import org.photon.entity.Branch;
import org.photon.entity.Customer;
import org.photon.entity.Order;
import org.photon.entity.Product;
import org.photon.entity.util.PaymentStatus;
import org.photon.entity.util.PaymentType;
import org.photon.ws.model.OrderCompleteModel;
import org.photon.ws.model.OrderMessageModel;
import org.photon.ws.model.OrderProductModel;

/**
 * REST Web Service
 *
 * @author Photon
 */
@Path("orders")
@RequestScoped
public class OrdersResource {

    @Context
    private UriInfo context;

    private OrderFacadeLocal orderRef;
    private CustomerFacadeLocal cutomerOrderRef;
    private BranchFacadeLocal branchOrderRef;
    private AddressFacadeLocal addressOrderRef;
    private ProductFacadeLocal productOrderRef;

    /**
     * Creates a new instance of AccountResource
     */
    public OrdersResource() {

        try {
            orderRef = (OrderFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/OrderEJB");
            cutomerOrderRef = (CustomerFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/CustomerEJB");
            branchOrderRef = (BranchFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/BranchEJB");
            addressOrderRef = (AddressFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/AddressEJB");
            productOrderRef = (ProductFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/ProductEJB");
        } catch (NamingException ex) {
            Logger.getLogger(OrdersResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * PUT method for updating or creating an instance of OrdersResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderMessageModel putJson(OrderCompleteModel content) {
        System.out.println(" " + content.getAddressId() + " " + content.getBranchName() + " " + content.getToken());
        OrderCompleteModel comingModel = content;
        List<OrderProductModel> orderProductModels = comingModel.getSendProModels();
        for (OrderProductModel orderProductModel : orderProductModels) {
            System.out.println("order pro " + orderProductModel.getName());
        }
             
         Address a = addressOrderRef.find(Long.parseLong(content.getAddressId()));
        Branch b = branchOrderRef.find(Long.parseLong(content.getBranchId()));
        Customer c = cutomerOrderRef.find(Long.parseLong(content.getCustomerId()));
        Set<Product> proSet = new HashSet<>();
        List<OrderProductModel> productModels =  content.getSendProModels();
         int proCount = content.getSendProModels().size();
        System.out.println("product count" +proCount);
        Double totalPrice =0D;
               for(int i=0; i<proCount;i++)
                   {
                       Product p = new Product();
                      p = productOrderRef.find(Long.parseLong(productModels.get(i).getProId()));
                       System.out.println("prices "+p.getCurrentPrice());
                      totalPrice += p.getCurrentPrice().doubleValue();
                      proSet.add(p);
                                          }
                Order order = new Order();
              BigDecimal totalPriceB  = new BigDecimal(totalPrice);
                order.setCost(totalPriceB);
                order.setAddress(a);
        order.setBranch(b);
        order.setCustomer(c);
        order.setProducts(proSet);
        orderRef.create(order);
        
         OrderMessageModel messageModel = new OrderMessageModel();
        messageModel.setOrderMessage("Thank You. YourOrder has been received!");
        messageModel.setOrderId(order.getOrderId());
        messageModel.setOrderTime(new Date());
        messageModel.setTotalPrice(totalPrice);
        
               
        /*
        //----------------------------------------------------------------------------------

        Address a = addressOrderRef.find(1L);
        Branch b = branchOrderRef.find(1L);
        Customer c = cutomerOrderRef.find(1L);
        Product p1 = productOrderRef.find(1L);
        Product p2 = productOrderRef.find(10L);
        Product p3 = productOrderRef.find(15L);
        Set<Product> proSet = new HashSet<>();
        proSet.add(p1);
        proSet.add(p2);
        proSet.add(p3);
        Order order = new Order();
        order.setCost(new BigDecimal(101));
        order.setPaymentType(PaymentType.PAYING_AT_DOOR);
        order.setPaymentStatus(PaymentStatus.PROCESSED);
        order.setAddress(a);
        order.setBranch(b);
        order.setCustomer(c);
        order.setProducts(proSet);
        orderRef.create(order);
        */
        

        return messageModel;
    }
}

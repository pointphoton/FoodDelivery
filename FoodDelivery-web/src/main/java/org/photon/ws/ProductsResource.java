/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
import javax.ws.rs.QueryParam;
import org.photon.ejb.ProductFacadeLocal;
import org.photon.entity.Product;
import org.photon.ws.model.ProductModel;

/**
 * REST Web Service
 *
 * @author Photon
 */
@Path("products")
@RequestScoped
public class ProductsResource {

    @Context
    private UriInfo context;

     
    private ProductFacadeLocal productRef;
    
    public ProductsResource() {
        try {
            productRef = (ProductFacadeLocal)new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/ProductEJB");
        } catch (NamingException ex) {
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of org.photon.ws.ProductsResource
     * @param start
     * @param size
     * @return List<ProductModel>
     */
    @GET
    @Produces("application/json")
    public List<ProductModel> getProducts(@QueryParam("start") int start, @QueryParam("size") int size) {
   
        List<ProductModel> pmList = new ArrayList<>();
        ProductModel pm = null ;
     List<Product> products =new ArrayList<>() ;
        for (Product product : products) {
            System.out.println(" product ref : "+product.getName());
        }
        if(start >=0 && size >= 1 ){
            products = productRef.findAllPaginated(start,size);
                  for (Product product : products) {
             pm=new ProductModel(product.getProductId(), product.getName(),product.getProDescription(),product.getCurrentPrice());
             pmList.add(pm);
          
        }             
		}
          products = productRef.findAll();
         for (Product product : products) {
             pm=new ProductModel(product.getProductId(), product.getName(),product.getProDescription(),product.getCurrentPrice());
             pmList.add(pm);
            System.out.println(" product ref : "+product.getName());
        }
      return pmList ;
    }

    /**
     * PUT method for updating or creating an instance of ProductsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}

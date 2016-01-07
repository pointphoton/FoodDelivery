/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.photon.ejb.AddressFacadeLocal;
import org.photon.ejb.CustomerFacadeLocal;
import org.photon.entity.Address;
import org.photon.ws.model.AddressModel;
import org.photon.ws.model.GetCustomerAddressModel;

/**
 * REST Web Service
 *
 * @author Photon
 */
@Path("addresses")
@RequestScoped
public class AddressesResource {

    @Context
    private UriInfo context;

    private AddressFacadeLocal addressRef;
  private CustomerFacadeLocal customerRef;
    /**
     * Creates a new instance of AddressesResource
     */
    public AddressesResource() {

        try {
            addressRef = (AddressFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/AddressEJB");
        } catch (NamingException ex) {
            Logger.getLogger(AddressesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            customerRef = (CustomerFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/CustomerEJB");
        } catch (NamingException ex) {
            Logger.getLogger(AddressesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of org.photon.ws.AddressesResource
     *
     * @param token
     * @param input
     * @param response
     * @return an instance of org.photon.ws.model.AddressModel
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddressModel> getJson(GetCustomerAddressModel input,
            @Context HttpServletResponse response) {

        
        Long id= (null!=input.getId())?Long.parseLong(input.getId()) : -1L ;
      
        
  if( ! customerRef.confirmToken(input.getEmail(), input.getToken())||id<1)
          {
               response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "BAD REQUEST ID, EMAIL OR TOKEN  ERROR" );
            return null;
          }
     
       List<Address> addresses = addressRef.findByCustomerId(id);
           AddressModel addressModel1 = new AddressModel(id,addresses.get(0).getAddressid(),addresses.get(0).getDistrict(),addresses.get(0).getAddresstext());
         AddressModel addressModel2 = new AddressModel(id,addresses.get(1).getAddressid(),addresses.get(1).getDistrict(),addresses.get(1).getAddresstext());
      List<AddressModel> addressModels = new ArrayList<>();
      addressModels.add(addressModel1);
      addressModels.add(addressModel2);
              
response.setStatus(Response.Status.ACCEPTED.getStatusCode());
        return addressModels ;
    }

    /**
     * PUT method for updating or creating an instance of AddressesResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(AddressModel content) {
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws;

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
import org.photon.ejb.CustomerFacadeLocal;
import org.photon.entity.Customer;
import org.photon.ws.model.AccountModel;
import org.photon.ws.util.TokenUtil;

/**
 * REST Web Service
 *
 * @author Photon
 */
@Path("account")
@RequestScoped
public class AccountResource {

    @Context
    private UriInfo context;

    private CustomerFacadeLocal customerRef;

    /**
     * Creates a new instance of AccountResource
     */
    public AccountResource() {

        try {
            customerRef = (CustomerFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/CustomerEJB");
        } catch (NamingException ex) {
            Logger.getLogger(AccountResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of org.photon.ws.AccountResource
     *
     * @param token
     * @param input
     * @param response
     * @return an instance of org.photon.ws.model.AccountModel
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createAccount(
            AccountModel input, @Context HttpServletResponse response) {
        if (input.getEmail() == null || input.getEmail().equals("") || input.getPassword() == null || input.getPassword().isEmpty()
                || input.getUserName() == null || input.getUserName().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Customer customer = new Customer();
        customer.setEmail(input.getEmail());
        customer.setPassword(input.getPassword());
        customer.setUserName(input.getUserName());
        String sendToken = TokenUtil.getToken(input.getEmail(), input.getPassword());
        customer.setToken(sendToken);
        Long id = customerRef.save(customer);
        if (id != null || id > 0) {
            response.setHeader("AuthHeader", sendToken);
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();

    }

    /**
     * PUT method for updating or creating an instance of AccountResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(AccountModel content) {
    }
}

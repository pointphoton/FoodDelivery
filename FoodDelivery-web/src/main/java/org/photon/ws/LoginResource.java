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
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.photon.ejb.CustomerFacadeLocal;
import org.photon.entity.Customer;
import org.photon.ws.model.LoginModel;
import org.photon.ws.model.AccountModel;
import org.photon.ws.model.LoginSendModel;
import org.photon.ws.util.TokenUtil;

/**
 * REST Web Service
 *
 * @author Photon
 */
@Path("login")
@RequestScoped
public class LoginResource {

    @Context
    private UriInfo context;

    private CustomerFacadeLocal customerEJBRef;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
        try {
            customerEJBRef = (CustomerFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/CustomerEJB");
        } catch (NamingException ex) {
            Logger.getLogger(LoginResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of org.photon.ws.LoginResource
     *
     * @param token
     * @param input
     * @param response
     *
     * @return LoginModel
     *
     * CAUTION : DONT FORGET!!! ***IMPORTANT: THIS METHOD IS USING HTTPS PORT TO PROVIDE SAFETY OF HEADER PARAMS!!!.... https://localhost:8186/FoodDelivery-web/webapi/login
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLogintoken(@HeaderParam("AuthHeader") String token,
            LoginModel input, @Context HttpServletResponse response) {
        if (input.getEmail() == null || input.getEmail().equals("") || input.getPassword() == null || input.getPassword().isEmpty()
                || token == null || token.isEmpty()) {
            System.out.println("header token :" + token);
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "nullable problem");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if (!customerEJBRef.confirmToken(token)) {
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "confirm token error");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if (!customerEJBRef.confirmEmailPassword(input.getEmail(), input.getPassword())) {
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "confirm email /  password error");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Customer c = customerEJBRef.findByMailAndPass(input.getEmail(), input.getPassword());
        if (c == null) {
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "Database has retuned null customer");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String newToken = TokenUtil.getToken(input.getEmail(), input.getPassword());
        System.out.println("token gelen : " + newToken);
        c.setToken(newToken);
        try {
            customerEJBRef.edit(c);
            response.setHeader("AuthHeader", newToken);
            return Response.accepted().build();
        } catch (Exception e) {
            System.out.println("edit problem : ");
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    /**
     * PUT method for updating or creating an instance of LoginResource
     *
     * @param token
     * @param input
     * @param response
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LoginSendModel getLogin(@HeaderParam("AuthHeader") String token,LoginModel input, @Context HttpServletResponse response) {
        if (input.getEmail() == null || input.getEmail().equals("") || input.getPassword() == null || input.getPassword().isEmpty()) {
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "nullable problem");
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            return null ;
        }

        if (!customerEJBRef.confirmEmailPassword(input.getEmail(), input.getPassword())) {
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "confirm email /  password error");
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            return null ;
        }

        Customer c = customerEJBRef.findByMailAndPass(input.getEmail(), input.getPassword());
        if (c == null) {
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "Database has retuned null customer");
              response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            return null ;
        } else {
        
              String newToken = TokenUtil.getToken(input.getEmail(), input.getPassword());
                   c.setToken(newToken);
                    try {
            customerEJBRef.edit(c);
            response.setHeader("AuthHeader", newToken);
            response.setStatus(Response.Status.ACCEPTED.getStatusCode());
           return new LoginSendModel(c.getCustomerId(),c.getEmail());
        } catch (Exception e) {
            System.out.println("edit problem : ");
              Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, "Database Error edit customer");
          response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            return null ;
        }
              
           
          
    }

}
}

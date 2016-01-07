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
import org.photon.ejb.BranchFacadeLocal;
import org.photon.entity.Branch;
import org.photon.ws.model.BranchModel;

/**
 * REST Web Service
 *
 * @author Photon
 */
@Path("branches")
@RequestScoped
public class BranchResource {

    @Context
    private UriInfo context;
    
    private BranchFacadeLocal branchEjbRef ;

    /**
     * Creates a new instance of BranchResource
     */
    public BranchResource() {
         try {
            branchEjbRef = (BranchFacadeLocal) new InitialContext().lookup("java:global/FoodDelivery-ear/FoodDelivery-ejb-1.0-SNAPSHOT/BranchEJB");
        } catch (NamingException ex) {
            Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    /**
     * Retrieves representation of an instance of org.photon.ws.BranchResource
     * @return an instance of org.photon.ws.model.BranchModel
     */
    @GET
    @Produces("application/json")
    public List<BranchModel> getBranches() {
List<Branch> branches =     branchEjbRef.findAll();
List<BranchModel> branchModels = new ArrayList<>();
branches.stream().forEach((branch) -> {
    try {
         branchModels.add(new BranchModel(branch.getBranchId(),branch.getName(),branch.getBranchAddress()));
    } catch (Exception e) {
           Logger.getLogger(ProductsResource.class.getName()).log(Level.SEVERE, null, e);
    }
   
});
        return  branchModels;
    }

    /**
     * PUT method for updating or creating an instance of BranchResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(BranchModel content) {
    }
}

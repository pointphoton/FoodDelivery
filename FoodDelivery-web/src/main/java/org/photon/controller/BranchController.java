/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.controller;


import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import org.photon.ejb.BranchFacadeLocal;
import org.photon.entity.Branch;
import org.photon.entity.util.BranchName;

@Named("branchCont")
@SessionScoped
public class BranchController implements Serializable{
    
    @EJB(name = "BranchEJB")
   private BranchFacadeLocal branchEJB;
    
    private Branch branch ;
    
    @PostConstruct
   public void init(){
    branch = new Branch();
    
            
    }
    
   public  void addBranch(){
      try {
            branchEJB.create(branch);
        } catch (Exception e) {
            //Logging
        }
    }
    
   public  void addBranches(){
        Branch br = null;
        for (BranchName b : BranchName.values()) {
            br = new Branch(b.getValue(),b.getValue()+" A Sokak No :1");
            branchEJB.create(br);
            System.out.println(br+" "+br.getName());
        }
        
    
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    
    
    
}

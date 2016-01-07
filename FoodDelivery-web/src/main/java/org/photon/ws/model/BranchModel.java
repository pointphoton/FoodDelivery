/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.ws.model;

import javax.enterprise.inject.Model;

@Model
public class BranchModel {
    
        private Long branchId;
    private String branchName;  
    private String branchAddress;

    public BranchModel() {
    }

    public BranchModel(Long branchId, String branchName, String branchAddress) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }

    
    /**
     * Get the value of branchAddress
     *
     * @return the value of branchAddress
     */
    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * Set the value of branchAddress
     *
     * @param branchAddress new value of branchAddress
     */
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
  

    /**
     * Get the value of branchName
     *
     * @return the value of branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Set the value of branchName
     *
     * @param branchName new value of branchName
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * Get the value of branchId
     *
     * @return the value of branchId
     */
    public Long getBranchId() {
        return branchId;
    }

    /**
     * Set the value of branchId
     *
     * @param branchId new value of branchId
     */
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

}

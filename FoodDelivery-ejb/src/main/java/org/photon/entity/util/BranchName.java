/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.photon.entity.util;

/**
 *
 * @author Photon
 */
public enum BranchName {
    
    ATAKOY("Ataköy"),BAKIRKOY("Bakırköy"),BAHCELIEVLER("Bahçelievler"),YESILKOY("Yeşilköy"),YUCE_TARLA("Yüce Tarla"),FLORYA("Florya"),GUNGOREN("Güngören");

    private final String value;
     private BranchName(String value) {
    this.value = value ;
    }

    public String getValue() {
        return value;
    }
     
    
    
    
}

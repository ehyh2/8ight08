/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Charmaine
 */
@Entity
public class RetailProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long modelID;
    private String modelName;
    private String modelDescription;
    private double modelPrice;
    private String countryOrigin;
    private int minQty;

    public RetailProductEntity() {
    }

    public RetailProductEntity(String modelName, String modelDescription, double modelPrice, String countryOrigin, int minQty) {
        this.modelName = modelName;
        this.modelDescription = modelDescription;
        this.modelPrice = modelPrice;
        this.countryOrigin = countryOrigin;
        this.minQty = minQty;
    }

    public Long getModelID() {
        return modelID;
    }

    public void setModelID(Long modelID) {
        this.modelID = modelID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public double getModelPrice() {
        return modelPrice;
    }

    public void setModelPrice(double modelPrice) {
        this.modelPrice = modelPrice;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public int getMinQty() {
        return minQty;
    }

    public void setMinQty(int minQty) {
        this.minQty = minQty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelID != null ? modelID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetailProductEntity)) {
            return false;
        }
        RetailProductEntity other = (RetailProductEntity) object;
        if ((this.modelID == null && other.modelID != null) || (this.modelID != null && !this.modelID.equals(other.modelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RetailProductEntity[ id=" + modelID + " ]";
    }
    
}

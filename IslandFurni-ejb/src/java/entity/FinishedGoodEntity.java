/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Liwei__xD
 */
@Entity
public class FinishedGoodEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long modelID;
    private String modelName; 
    private String modelDescription; 
    private double modelPrice; 
    private String countryOrigin; 
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="FinishedGoodEntity_RawMaterialEntity")
    private List<RawMaterialEntity> parts = new ArrayList<>(); 
    private List partQty;

    public FinishedGoodEntity() {
    }

    public FinishedGoodEntity(String modelName, String modelDescription, double modelPrice, String countryOrigin) {
        this.modelName = modelName;
        this.modelDescription = modelDescription;
        this.modelPrice = modelPrice;
        this.countryOrigin = countryOrigin;
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

    public List<RawMaterialEntity> getParts() {
        return parts;
    }

    public void setParts(List<RawMaterialEntity> parts) {
        this.parts = parts;
    }

    public List getPartQty() {
        return partQty;
    }

    public void setPartQty(List partQty) {
        this.partQty = partQty;
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
        if (!(object instanceof FinishedGoodEntity)) {
            return false;
        }
        FinishedGoodEntity other = (FinishedGoodEntity) object;
        if ((this.modelID == null && other.modelID != null) || (this.modelID != null && !this.modelID.equals(other.modelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FinishedGoodEntity[ id=" + modelID + " ]";
    }
}

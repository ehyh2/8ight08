/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author User
 */
@Entity
public class FinishedGoodEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String modelName;
    private String modelDescription;
    private double modelPrice;
    
    @ManyToMany
    @JoinTable(name="FinishedGoodEntity_RawMaterialEntity")
    private Set<RawMaterialEntity> rawMats = new HashSet<RawMaterialEntity>();
    
    @OneToOne
    private BOMEntity bom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BOMEntity getBom() {
        return bom;
    }

    public void setBom(BOMEntity bom) {
        this.bom = bom;
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

    public Set<RawMaterialEntity> getRawMats() {
        return rawMats;
    }

    public void setRawMats(Set<RawMaterialEntity> rawMats) {
        this.rawMats = rawMats;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinishedGoodEntity)) {
            return false;
        }
        FinishedGoodEntity other = (FinishedGoodEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FinishedGoodEntity[ id=" + id + " ]";
    }
    
}

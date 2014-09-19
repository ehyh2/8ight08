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
import javax.persistence.OneToOne;

/**
 *
 * @author Esther Hong
 */
@Entity
public class POQtyEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private int qty;
    
    @OneToOne
    private RawMaterialEntity rawMat;
    @OneToOne
    private FinishedGoodEntity finishedGood;
    @OneToOne
    private RetailProductEntity retailPdt;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public RawMaterialEntity getRawMat() {
        return rawMat;
    }

    public void setRawMat(RawMaterialEntity rawMat) {
        this.rawMat = rawMat;
    }

    public FinishedGoodEntity getFinishedGood() {
        return finishedGood;
    }

    public void setFinishedGood(FinishedGoodEntity finishedGood) {
        this.finishedGood = finishedGood;
    }

    public RetailProductEntity getRetailPdt() {
        return retailPdt;
    }

    public void setRetailPdt(RetailProductEntity retailPdt) {
        this.retailPdt = retailPdt;
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
        if (!(object instanceof POQtyEntity)) {
            return false;
        }
        POQtyEntity other = (POQtyEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.POQtyEntity[ id=" + id + " ]";
    }
    
}
